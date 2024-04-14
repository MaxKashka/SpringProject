package networklist1;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Book bookId;
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;
    public Long getLoanId() {
        return loanId;
    }
    public Book getBook() {
        return bookId;
    }
    public User getUser() {
        return userId;
    }
    public Date getLoanDate() {
        return loanDate;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public Date getReturnDate() {
        return returnDate;
    }
    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }
    public void setBook(Book bookId){
        this.bookId = bookId;
    }
    public void setUser(User userId) {
        this.userId = userId;
    }
    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
