package com.example.cooked_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cooked_app.Activities.YouTubeActivity;
import com.example.cooked_app.Model.Course_Model;
import com.example.cooked_app.R;

import java.io.Serializable;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private static List<Course_Model> courses;
    private static Context context;
    private Button play_Video;

    public CourseAdapter(Context context, List<Course_Model> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_card_item, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course_Model course = courses.get(position);
        holder.courseName.setText(course.getName());
        holder.courseDescription.setText(course.getDescription());
        holder.courseThumbnail.setImageResource(course.getThumbnail());
        holder.youtubeLink.setText(course.getName());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView courseName, courseDescription;
        Button youtubeLink;
        ImageView courseThumbnail;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.course_name);
            courseDescription = itemView.findViewById(R.id.course_description);
            courseThumbnail = itemView.findViewById(R.id.course_thumbnail);
            youtubeLink = itemView.findViewById(R.id.youtube_button);
            youtubeLink.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, YouTubeActivity.class);
            int pos = getAdapterPosition();
            intent.putExtra("Course", (Serializable) courses.get(pos));
            context.startActivity(intent);
        }
    }
}
