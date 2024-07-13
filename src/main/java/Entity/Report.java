package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;
    private String description;
    private String title;
    @ManyToOne
    @JoinColumn(name="follower_id",referencedColumnName = "email")
    private Follower reportBy;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="post_id",referencedColumnName = "postId")
    private Post reportedTo;
}
