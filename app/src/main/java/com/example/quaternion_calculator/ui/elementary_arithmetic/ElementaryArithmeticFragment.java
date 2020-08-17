package com.example.quaternion_calculator.ui.elementary_arithmetic;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.quaternion_calculator.R;
import com.example.quaternion_calculator.quaternions.Quaternion;
import com.example.quaternion_calculator.quaternions.QuaternionOperation;

public class ElementaryArithmeticFragment extends Fragment {

    private EditText s_1, x_1, y_1, z_1, s_2, x_2, y_2, z_2;
    private Button plus, minus, times, division, equal;
    private TextView s_result, x_result, y_result, z_result;
    private Drawable selected, deselected;

    private char selectedOperation = ' ';

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_elementary_arithmetic, container, false);

        init(root);

        // Mark selected button of elementary operation
        plus.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                // Select "+" button
                select(plus);
                selectedOperation = '+';

                // Deselect other operation-buttons
                deselect(minus);
                deselect(times);
                deselect(division);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                // Select "-" button
                select(minus);
                selectedOperation = '-';

                // Deselect other operation-buttons
                deselect(plus);
                deselect(times);
                deselect(division);
            }
        });

        times.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                // Select "*" button
                select(times);
                selectedOperation = '*';

                // Deselect other operation-buttons
                deselect(plus);
                deselect(minus);
                deselect(division);
            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                // Select "/" button
                select(division);
                selectedOperation = '/';

                // Deselect other operation-buttons
                deselect(plus);
                deselect(minus);
                deselect(times);
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOperation == ' ')
                    Toast.makeText(getActivity(), "Select an operation first", Toast.LENGTH_SHORT).show();

                else {

                    // Check for emptiness -> if this is the case enter 0.0 in the box
                    // Save the values
                    double s1, x1, y1, z1, s2, x2, y2, z2;

                    if (s_1.getText().toString().isEmpty()) {
                        s_1.setText("0.0");
                        s1 = 0;
                    } else
                        s1 = Double.parseDouble(s_1.getText().toString());

                    if (s_2.getText().toString().isEmpty()) {
                        s_2.setText("0.0");
                        s2 = 0;
                    } else
                        s2 = Double.parseDouble(s_2.getText().toString());

                    if (x_1.getText().toString().isEmpty()) {
                        x_1.setText("0.0");
                        x1 = 0;
                    } else
                        x1 = Double.parseDouble(x_1.getText().toString());

                    if (x_2.getText().toString().isEmpty()) {
                        x_2.setText("0.0");
                        x2 = 0;
                    } else
                        x2 = Double.parseDouble(x_2.getText().toString());

                    if (y_1.getText().toString().isEmpty()) {
                        y_1.setText("0.0");
                        y1 = 0;
                    } else
                        y1 = Double.parseDouble(y_1.getText().toString());

                    if (y_2.getText().toString().isEmpty()) {
                        y_2.setText("0.0");
                        y2 = 0;
                    } else
                        y2 = Double.parseDouble(y_2.getText().toString());

                    if (z_1.getText().toString().isEmpty()) {
                        z_1.setText("0.0");
                        z1 = 0;
                    } else
                        z1 = Double.parseDouble(z_1.getText().toString());

                    if (z_2.getText().toString().isEmpty()) {
                        z_2.setText("0.0");
                        z2 = 0;
                    } else
                        z2 = Double.parseDouble(z_2.getText().toString());

                    // Create Quaternions
                    Quaternion q1 = new Quaternion(s1, x1, y1, z1);
                    Quaternion q2 = new Quaternion(s2, x2, y2, z2);
                    Quaternion result = new Quaternion();

                    // Calculate the result
                    switch (selectedOperation) {
                        case '+':
                            result = QuaternionOperation.add(q1, q2);
                            break;
                        case '-':
                            result = QuaternionOperation.subtract(q1, q2);
                            break;
                        case '*':
                            //TODO
                            break;
                        case '/':
                            //TODO
                            break;
                        default:
                            Toast.makeText(getActivity(), "An error occured, please try it again.", Toast.LENGTH_SHORT).show();
                    }

                    // Show the result
                    // If s, x, y or z is negative -> put parentheses around it
                    if (result.getS() < 0)
                        s_result.setText("(" + result.getS() + ")");
                    else
                        s_result.setText(result.getS() + "");

                    if (result.getX() < 0)
                        x_result.setText("(" + result.getX() + ")");
                    else
                        x_result.setText(result.getX() + "");

                    if (result.getY() < 0)
                        y_result.setText("(" + result.getY() + ")");
                    else
                        y_result.setText(result.getY() + "");

                    if (result.getZ() < 0)
                        z_result.setText("(" + result.getZ() + ")");
                    else
                        z_result.setText(result.getZ() + "");
                }

            }
        });

        return root;
    }

    /*
    Deselect a button by changing its background and text color
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void deselect(Button btn) {
        btn.setBackground(deselected);
        btn.setTextColor(Color.WHITE);
    }

    /*
    Select a button by changing its background and text color
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void select(Button btn) {
        btn.setBackground(selected);
        btn.setTextColor(Color.BLACK);
    }

    private void init(View root) {

        // First quaternion
        s_1 = root.findViewById(R.id.s_elementary_arithmetic);
        x_1 = root.findViewById(R.id.x_elementary_arithmetic);
        y_1 = root.findViewById(R.id.y_elementary_arithmetic);
        z_1 = root.findViewById(R.id.z_elementary_arithmetic);

        // Second quaternion
        s_2 = root.findViewById(R.id.s_elementary_arithmetic2);
        x_2 = root.findViewById(R.id.x_elementary_arithmetic2);
        y_2 = root.findViewById(R.id.y_elementary_arithmetic2);
        z_2 = root.findViewById(R.id.z_elementary_arithmetic2);

        // Elementary operations
        plus = root.findViewById(R.id.btn_plus_elementary_arithmetic);
        minus = root.findViewById(R.id.btn_minus_elementary_arithmetic);
        times = root.findViewById(R.id.btn_times_elementary_arithmetic);
        division = root.findViewById(R.id.btn_division_elementary_arithmetic);

        // Equal button
        equal = root.findViewById(R.id.btn_equals_elementary_arithmetic);

        // Result quaternion
        s_result = root.findViewById(R.id.s_elementary_arithmetic3);
        x_result = root.findViewById(R.id.x_elementary_arithmetic3);
        y_result = root.findViewById(R.id.y_elementary_arithmetic3);
        z_result = root.findViewById(R.id.z_elementary_arithmetic3);

        // Backgrounds
        deselected = ContextCompat.getDrawable(this.getActivity(), R.drawable.border_button_deselected);
        selected = ContextCompat.getDrawable(this.getActivity(), R.drawable.border_button_selected);
    }
}
