//package dev.twominutes.notetaking.models;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//
//import java.time.LocalDate;
//
//@Data
//@Entity
//@NoArgsConstructor
//@Table(name = "goals")
//public class Goal {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String description;
//
//    @Column(nullable = false)
//    private LocalDate deadline;
//
//    @Column(nullable = false)
//    private String type;    // monthly, weekly, daily
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    public Goal(String description, LocalDate deadline, String type, User user) {
//        this.description = description;
//        this.deadline = deadline;
//        this.type = type;
//        this.user = user;
//    }
//}
