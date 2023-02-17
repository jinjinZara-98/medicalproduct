package capstonedesign.medicalproduct.dto.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Uploadedfile {

    private String uploadFileName;

    private String storeFileName;
}