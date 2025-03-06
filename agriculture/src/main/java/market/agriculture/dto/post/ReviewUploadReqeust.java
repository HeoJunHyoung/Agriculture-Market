package market.agriculture.dto.post;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewUploadReqeust {
    public ReviewUploadReqeust() {
    }

    String reviewTitle;
    String reviewDescription;

}
