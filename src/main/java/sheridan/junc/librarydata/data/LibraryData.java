package sheridan.junc.librarydata.data;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LibraryData {
    private List<Book> books;
}
