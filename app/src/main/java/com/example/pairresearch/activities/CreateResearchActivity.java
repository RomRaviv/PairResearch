package com.example.pairresearch.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pairresearch.App;
import com.example.pairresearch.R;
import com.example.pairresearch.models.enums.Degree;
import com.example.pairresearch.services.ApiService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateResearchActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_GALLERY = 1;

    private ApiService apiService = App.getApiService();

    private List<Uri> selectedImages = new ArrayList<>();
    private ActivityResultLauncher<Intent> imageGalleryLauncher;

    private TextView registerTextView;
    private ImageView logoImageView;
    private EditText researchNameEditText;
    private Spinner degreeSpinner;
    private EditText researchPaymentEditText;
    private EditText researchDescriptionEditText;
    private Button uploadImageButton;
    private ImageView imagePreviewImageView;
    private CheckBox finalProjectCheckBox;
    private Button createResearchButton;
    private String researchName;
    private String degree;
    private String researchPayment;
    private String researchDescription;
    private boolean isFinalProject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_research);

        findViews();
        initViews();
        populateSpinner();
        initImageLauncher();
    }

    private void populateSpinner() {
        Degree[] degrees = Degree.values();

        // Create an ArrayAdapter using the enum values and a default spinner layout
        ArrayAdapter<Degree> degreeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, degrees);

        // Set the layout resource for the drop-down items
        degreeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter to the spinner
        degreeSpinner.setAdapter(degreeAdapter);
    }

    private void openImageGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

        imageGalleryLauncher.launch(intent);
    }

    private void initImageLauncher(){
    imageGalleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
        result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    if (data.getClipData() != null) {
                        int count = data.getClipData().getItemCount();

                        // Clear the previously selected images
                        selectedImages.clear();

                        for (int i = 0; i < count; i++) {
                            Uri imageUri = data.getClipData().getItemAt(i).getUri();
                            selectedImages.add(imageUri);
                        }

                        // Display the first selected image in the preview
                        if (!selectedImages.isEmpty()) {
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImages.get(0));
                                imagePreviewImageView.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (data.getData() != null) {
                        Uri imageUri = data.getData();
                        selectedImages.clear();
                        selectedImages.add(imageUri);

                        // Display the selected image in the preview
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                            imagePreviewImageView.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    private void initViews() {
        uploadImageButton.setOnClickListener(v -> {
            openImageGallery();
        });

        createResearchButton.setOnClickListener(v -> {
            // Retrieve the content of each view and store it in variables
            researchName = researchNameEditText.getText().toString();
            degree = degreeSpinner.getSelectedItem().toString();
            researchPayment = researchPaymentEditText.getText().toString();
            researchDescription = researchDescriptionEditText.getText().toString();
            isFinalProject = finalProjectCheckBox.isChecked();

            // Perform validation
            if (researchName.isEmpty()) {
                researchNameEditText.setError("Research name is required");
                return;
            }

            if (researchPayment.isEmpty()) {
                researchPaymentEditText.setError("Research payment is required");
                return;
            }

            if (researchDescription.isEmpty()) {
                researchDescriptionEditText.setError("Research description is required");
                return;
            }

            RequestBody researchNamePart = RequestBody.create(
                    MediaType.parse("text/plain"), researchName);
            RequestBody degreePart = RequestBody.create(
                    MediaType.parse("text/plain"), degree);
            RequestBody researchPaymentPart = RequestBody.create(
                    MediaType.parse("text/plain"), researchPayment);
            RequestBody researchDescriptionPart = RequestBody.create(
                    MediaType.parse("text/plain"), researchDescription);
            RequestBody isFinalProjectPart = RequestBody.create(
                    MediaType.parse("text/plain"), String.valueOf(isFinalProject));

            List<MultipartBody.Part> imagesParts = new ArrayList<>();
            for (Uri imageUri : selectedImages) {
                // Use ContentResolver to get the actual path of the file
                String filePath = getPathFromURI(imageUri);
                // Create a file instance using the file path
                File file = new File(filePath);
                // Create a RequestBody instance from the file
                RequestBody requestFile = RequestBody.create(
                        MediaType.parse(getContentResolver().getType(imageUri)), file);
                // Create MultipartBody.Part using file request-body,file name and part name
                MultipartBody.Part body = MultipartBody.Part.createFormData(
                        "upload", file.getName(), requestFile);
                imagesParts.add(body);
            }

            // Use Retrofit to upload the research
            apiService.uploadResearch(
                    researchNamePart,
                    degreePart,
                    researchPaymentPart,
                    researchDescriptionPart,
                    isFinalProjectPart,
                    imagesParts
            ).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        // Handle the successful response
                    } else {
                        // Handle the error
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    // Handle the failure
                }
            });
        });
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor != null){
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                res = cursor.getString(column_index);
            }
            cursor.close();
        }
        return res;
    }

    private void findViews() {
        registerTextView = findViewById(R.id.register_uploads_text_view);
        logoImageView = findViewById(R.id.image_logo);
        researchNameEditText = findViewById(R.id.edit_text_research_name);
        degreeSpinner = findViewById(R.id.my_spinner);
        researchPaymentEditText = findViewById(R.id.edit_text_research_payment);
        researchDescriptionEditText = findViewById(R.id.edit_text_research_description);
        uploadImageButton = findViewById(R.id.btn_research_upload_image);
        imagePreviewImageView = findViewById(R.id.iv_research_image_preview);
        finalProjectCheckBox = findViewById(R.id.my_checkbox);
        createResearchButton = findViewById(R.id.btn_create_research_done);
    }
}