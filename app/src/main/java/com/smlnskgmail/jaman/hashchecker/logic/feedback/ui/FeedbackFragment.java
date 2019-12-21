package com.smlnskgmail.jaman.hashchecker.logic.feedback.ui;

import android.content.Intent;
import android.net.Uri;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.smlnskgmail.jaman.hashchecker.R;
import com.smlnskgmail.jaman.hashchecker.components.fragments.BaseFragment;
import com.smlnskgmail.jaman.hashchecker.logic.feedback.Feedback;

public class FeedbackFragment extends BaseFragment {

    private EditText feedbackEdit;

    private final Feedback feedback = new Feedback();

    @Override
    public void initializeContent(@NonNull final View contentView) {
        feedbackEdit = contentView.findViewById(R.id.et_feedback_message);

        applyInfoToTextView(
                contentView,
                R.id.tv_hash_checker_value,
                feedback.getAppInfo()
        );
        applyInfoToTextView(
                contentView,
                R.id.tv_android_value,
                feedback.getOsVersion()
        );
        applyInfoToTextView(
                contentView,
                R.id.tv_manufacturer_value,
                feedback.getManufacturer()
        );
        applyInfoToTextView(
                contentView,
                R.id.tv_model_value,
                feedback.getModel()
        );
    }

    private void applyInfoToTextView(
            @NonNull View contentView,
            int textViewResId,
            @NonNull String info
    ) {
        ((TextView) contentView.findViewById(textViewResId)).setText(info);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        } else if (item.getItemId() == R.id.menu_action_send_feedback) {
            sendEmail(
                    feedback.getConfiguredMessage(feedbackEdit.getText().toString()),
                    getString(R.string.common_email)
            );
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendEmail(@NonNull String text, @NonNull String email) {
        Intent emailIntent = new Intent(
                Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", email, null)
        );
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.common_app_name));
        emailIntent.putExtra(Intent.EXTRA_TEXT, text);

        String chooseMessage = String.format("%s:", getString(R.string.message_email_app_chooser));
        startActivity(Intent.createChooser(emailIntent, chooseMessage));
    }

    @Override
    public int getActionBarTitleResId() {
        return R.string.menu_title_feedback;
    }

    @Override
    public boolean setAllowBackAction() {
        return true;
    }

    @Override
    public int getBackActionIconResId() {
        return R.drawable.ic_close;
    }

    @Override
    public int getMenuResId() {
        return R.menu.menu_feedback;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_feedback;
    }

}