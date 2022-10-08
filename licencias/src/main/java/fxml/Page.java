package fxml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Page {
    private int idPage;
    private int fromItem;
    private int toItem;
}
