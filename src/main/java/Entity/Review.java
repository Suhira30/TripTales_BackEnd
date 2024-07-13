package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    private String description;
    private Double rating;
    private LocalDateTime postedAt;
    @ManyToOne (cascade = CascadeType.DETACH)
    @JoinColumn(name="post_id" ,referencedColumnName = "postId")
    private Post reviewTo;
    @ManyToOne
    @JoinColumn(name="follower_id", referencedColumnName = "email")
    private Follower reviewBy;
}
