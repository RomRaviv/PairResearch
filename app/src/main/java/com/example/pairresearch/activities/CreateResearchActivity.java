package com.example.pairresearch.activities;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pairresearch.R;
import com.example.pairresearch.models.enums.Degree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateResearchActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_GALLERY = 1;
    private List<Uri> selectedImages = new ArrayList<>();


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
        startActivityForResult(Intent.createChooser(intent, "Select Images"), REQUEST_IMAGE_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK) {
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
    }

    private void initViews() {
        uploadImageButton.setOnClickListener(v -> openImageGallery());

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
        });
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