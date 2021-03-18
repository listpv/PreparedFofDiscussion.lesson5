package ru.geekbrains;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "name")
        private String name;

        @Column(name = "mark")
        private Integer mark;

        public Student() {
        }

        public Student(String name, Integer mark) {
            this.name = name;
            this.mark = mark;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getMark() {
            return mark;
        }

        public void setMark(Integer mark) {
            this.mark = mark;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", mark=" + mark +
                    '}';
        }
}
