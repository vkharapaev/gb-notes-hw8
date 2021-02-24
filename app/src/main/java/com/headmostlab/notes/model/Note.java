package com.headmostlab.notes.model;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;

import java.util.Date;

public class Note implements Parcelable {
    private String title;
    private String description;
    private Date creationDate;

    public Note() {
    }

    public Note(String title, String description, Date creationDate) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
    }

    protected Note(Parcel in) {
        title = in.readString();
        description = in.readString();
        creationDate = new Date(in.readLong());
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    public String toHumanString() {
        return String.format("%s\n%s\n%s", title, description, creationDate);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeLong(creationDate.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public static class Builder {

        private final Note note;

        public Builder() {
            note = new Note();
        }

        public Builder setTitle(String title) {
            note.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            note.description = description;
            return this;
        }

        public Builder setCreateDate(Date creationDate) {
            note.creationDate = creationDate;
            return this;
        }

        public Note build() {
            return note;
        }
    }
}
