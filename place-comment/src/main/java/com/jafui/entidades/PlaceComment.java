package com.jafui.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_place_comment")
public class PlaceComment {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @NotBlank
        @Size(min = 2, max = 140)
        private String comment;

        @NotBlank
        @Column(name = "user_email")
        private String userEmail;

        @NotBlank
        @Column(name = "user_name")
        private String userName;

        @NotNull
        @JsonFormat(pattern="yyyy-MM-dd")
        @Column(name = "created_at")
        private LocalDate createdAt;

        @NotBlank
        @Column(name = "id_place")
        private String idPlace;


    public PlaceComment() {
            super();
    }

        public PlaceComment(Long id, String comment, String userEmail, String userName, LocalDate createdAt, String idPlace) {
                super();
                this.id = id;
                this.comment = comment;
                this.userEmail = userEmail;
                this.userName = userName;
                this.createdAt = createdAt;
                this.idPlace = idPlace;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getComment() {
                return comment;
        }

        public void setComment(String comment) {
                this.comment = comment;
        }

        public String getUserEmail() {
                return userEmail;
        }

        public void setUserEmail(String userEmail) {
                this.userEmail = userEmail;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public LocalDate getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(LocalDate createdAt) {
                this.createdAt = createdAt;
        }

        public String getIdPlace() {
                return idPlace;
        }

        public void setIdPlace(String idPlace) {
                this.idPlace = idPlace;
        }

        @Override
        public String toString() {
                return "PlaceComment{" +
                        "id=" + id +
                        ", comment='" + comment + '\'' +
                        ", userEmail='" + userEmail + '\'' +
                        ", userName='" + userName + '\'' +
                        ", createdAt=" + createdAt +
                        ", idPlace='" + idPlace + '\'' +
                        '}';
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                PlaceComment that = (PlaceComment) o;
                return Objects.equals(id, that.id) && Objects.equals(comment, that.comment) && Objects.equals(userEmail, that.userEmail) && Objects.equals(userName, that.userName) && Objects.equals(createdAt, that.createdAt) && Objects.equals(idPlace, that.idPlace);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, comment, userEmail, userName, createdAt, idPlace);
        }
}
