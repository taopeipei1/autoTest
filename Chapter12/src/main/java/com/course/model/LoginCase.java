package com.course.model;

import lombok.Data;

@Data
public class LoginCase {
        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getExpected() {
                return expected;
        }

        public void setExpected(String expected) {
                this.expected = expected;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        private int id;
        private String userName;
        private String password;
        private String expected;
}
