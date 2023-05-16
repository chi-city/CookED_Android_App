package com.example.cooked_app.ui.account;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Application;

import com.example.cooked_app.Activities.loginActivity;
import com.example.cooked_app.MyApp;
import com.example.cooked_app.R;

public class AccountFragment extends Fragment {

    private AccountViewModel mViewModel;
    private EditText userNameEditText;
    private EditText userEmailEditText;
    private Button editButton;
    private Button uploadPictureButton;
    private TextView sectionTitleTextView;
    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        // Find views by their IDs
        userNameEditText = view.findViewById(R.id.user_name);
        editButton = view.findViewById(R.id.edit_button);
        sectionTitleTextView = view.findViewById(R.id.section_title);
        userEmailEditText = view.findViewById(R.id.user_email);

        ImageView userPicture = view.findViewById(R.id.user_picture);
        userPicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            userPicture.setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    int diameter = Math.min(view.getWidth(), view.getHeight());
                    outline.setOval(0, 0, diameter, diameter);
                }
            });
            userPicture.setClipToOutline(true);
        }

        // Add click listeners to the buttons
        editButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                userNameEditText.setEnabled(true);
                userEmailEditText.setEnabled(true);
                userNameEditText.setSelection(userNameEditText.getText().length());
                showSoftKeyboard(userNameEditText);
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Save the new value when the user hits "Done" on the keyboard
                if (s.toString().endsWith("\n")) {
                    s.delete(s.length()-1, s.length());
                    userNameEditText.setEnabled(false);
                    userEmailEditText.setEnabled(false);
                    hideSoftKeyboard(userNameEditText);
                }
            }
        };

        userNameEditText.addTextChangedListener(textWatcher);
        userEmailEditText.addTextChangedListener(textWatcher);

        userNameEditText.setText(MyApp.global_name);
        userEmailEditText.setText(MyApp.global_email);
        // Set the text for the TextView
        sectionTitleTextView.setText("Additional Options");

        Button logoutButton = view.findViewById(R.id.option3_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), loginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager)
                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem menuItem = menu.findItem(R.id.action_search);
        menuItem.setVisible(false);
        // super.onCreateOptionsMenu(menu, inflater);
    }
}