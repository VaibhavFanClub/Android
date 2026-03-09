package com.example.sqlite;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private EditText courseNameEdt, courseTracksEdt, courseId, courseDurationEdt, courseDescriptionEdt;
    private Button addCourseBtn,getCourse,update,delete;
    private DBHandler dbHandler;
    ListView listView;
    TextView id,name,duration,description,tracks;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseId = findViewById(R.id.courseId);
        courseTracksEdt = findViewById(R.id.idEdtCourseTracks);
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        addCourseBtn = findViewById(R.id.idBtnAddCourse);
        getCourse = findViewById(R.id.getCourse);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.button);
        listView = findViewById(R.id.listView);

        dbHandler = new DBHandler(MainActivity.this);
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = courseNameEdt.getText().toString();
                String courseTracks = courseTracksEdt.getText().toString();
                String courseDuration = courseDurationEdt.getText().toString();
                String courseDescription = courseDescriptionEdt.getText().toString();

                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbHandler.addNewCourse(courseName, courseDuration, courseDescription, courseTracks);
                // after adding the data we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Course has been added.", Toast.LENGTH_SHORT).show();
                courseNameEdt.setText("");
                courseDurationEdt.setText("");
                courseTracksEdt.setText("");
                courseDescriptionEdt.setText("");
            }
        });

        getCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> allCourse = new ArrayList<>();
                ArrayList<CourseDetail> courseDetails = dbHandler.fetchDetails();
                for (int i = 0; i < courseDetails.size(); i++) {
                    allCourse.add(String.valueOf(courseDetails.get(i).id));
                    allCourse.add(courseDetails.get(i).courseName);
                    allCourse.add(courseDetails.get(i).courseDescription);
                    allCourse.add(courseDetails.get(i).courseDuration);
                    allCourse.add(courseDetails.get(i).courseTrack);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, allCourse);
                listView.setAdapter(arrayAdapter);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseDetail courseDetail = new CourseDetail();

                String courseName = courseNameEdt.getText().toString();
                String courseTracks = courseTracksEdt.getText().toString();
                String courseDuration = courseDurationEdt.getText().toString();
                String courseDescription = courseDescriptionEdt.getText().toString();
                int cid = Integer.parseInt(courseId.getText().toString());
                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                courseDetail.id = cid;
                courseDetail.courseName = courseName;
                courseDetail.courseDuration = courseDuration;
                courseDetail.courseTrack = courseTracks;
                courseDetail.courseDescription = courseDescription;
                dbHandler.updateCourse(courseDetail);

                courseNameEdt.setText("");
                courseDurationEdt.setText("");
                courseTracksEdt.setText("");
                courseDescriptionEdt.setText("");
                courseId.setText("");
                Toast.makeText(MainActivity.this, "Course has been updated.", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int cid=Integer.parseInt(courseId.getText().toString());
                dbHandler.deleteCourse(cid);

                courseNameEdt.setText("");
                courseDurationEdt.setText("");
                courseTracksEdt.setText("");
                courseDescriptionEdt.setText("");
                courseId.setText("");
                Toast.makeText(MainActivity.this, "Course has been deleted.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}