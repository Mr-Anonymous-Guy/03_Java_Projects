package app.service;

import app.dao.BookDAO;
import app.dao.MemberDAO;
import app.model.Book;
import app.model.Member;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class LibraryService {
    private BookDAO bookDAO;
    private MemberDAO memberDAO;

    public LibraryService() {
        this.bookDAO = new BookDAO();
        this.memberDAO = new MemberDAO();
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public List<Member> getAllMembers() {
        return memberDAO.getAllMembers();
    }

    public void exportBooksToCSV(String filePath) throws Exception {
        List<Book> books = getAllBooks();
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            writer.println("ID,Title,Author,ISBN,Published Year,Total Copies,Available Copies");
            for (Book b : books) {
                writer.printf("%d,%s,%s,%s,%d,%d,%d\n",
                    b.getId(), b.getTitle(), b.getAuthor(), b.getIsbn(),
                    b.getPublishedYear(), b.getTotalCopies(), b.getAvailableCopies());
            }
        }
    }
}
