package com.digicert.coding.service.impl;

import com.digicert.coding.exception.BookAlreadyExistsException;
import com.digicert.coding.exception.BookNotFoundException;
import com.digicert.coding.exception.InvalidISBNException;
import com.digicert.coding.model.BookModel;
import com.digicert.coding.repository.BookRepository;
import com.digicert.coding.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;
    private BookService bookService;
    AutoCloseable autoCloseable;
    BookModel goodBookModel;
    BookModel updateBookModel;
    BookModel badBookModel;
    BookModel badBookModel2;
    BookModel badBookModel3;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        bookService = new BookServiceImpl(bookRepository);
        goodBookModel = new BookModel("12345", "Don Quixote", "Don Quixote has become so entranced by reading chivalric romances that he determines to become a knight-errant himself. In the company of his faithful squire, Sancho Panza, his exploits blossom in all sorts of wonderful ways. While Quixote's fancy often leads him astray—he tilts at windmills, imagining them to be giants—Sancho acquires cunning and a certain sagacity. Sane madman and wise fool, they roam the world together, and together they have haunted readers' imaginations for nearly four hundred years.", "Miguel de Cervantes Saavedra", "Satire", "1605", 39.99);
        updateBookModel = new BookModel("33333", "The Hitchhiker's Guide to the Galaxy", "Seconds before the Earth is demolished to make way for a galactic freeway, Arthur Dent is plucked off the planet by his friend Ford Prefect, a researcher for the revised edition of The Hitchhiker's Guide to the Galaxy who, for the last fifteen years, has been posing as an out-of-work actor. Together this dynamic pair begin a journey through space aided by quotes from The Hitchhiker's Guide (\"A towel is about the most massively useful thing an interstellar hitchhiker can have\") and a galaxy-full of fellow travelers: Zaphod Beeblebrox--the two-headed, three-armed ex-hippie and totally out-to-lunch president of the galaxy; Trillian, Zaphod's girlfriend (formally Tricia McMillan), whom Arthur tried to pick up at a cocktail party once upon a time zone; Marvin, a paranoid, brilliant, and chronically depressed robot; Veet Voojagig, a former graduate student who is obsessed with the disappearance of all the ballpoint pens he bought over the years. Where are these pens? Why are we born? Why do we die? Why do we spend so much time between wearing digital watches? For all the answers stick your thumb to the stars. And don't forget to bring a towel!", "Douglas Adams", "Science Fiction", "1979", 99999.99);
        badBookModel = new BookModel(null, null, null, null, null, null, 0);
        badBookModel2 = new BookModel("11111", null, null, null, null, null, 0);
        badBookModel3 = new BookModel("98765", null, null, null, null, null, 0);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testGetAllBooks() {
        mock(BookModel.class);
        mock(BookRepository.class);

        when(bookRepository.findAll()).thenReturn(new ArrayList<BookModel>(Collections.singleton(goodBookModel)));
        assertThat(bookService.getAllBooks().get(0)).isEqualTo(goodBookModel);
    }

    @Test
    void testGoodGetBook() {
        mock(BookModel.class);
        mock(BookRepository.class);

        when(bookRepository.findById(any())).thenReturn(Optional.ofNullable(goodBookModel));
        assertThat(bookService.getBook(goodBookModel.getISBN())).isEqualTo(goodBookModel);
    }

    @Test
    void testBadGetBook() {
        mock(BookModel.class);
        mock(BookRepository.class);

        when(bookRepository.findById(any())).thenReturn(Optional.ofNullable(null));
        assertThatThrownBy(() -> { bookService.getBook(goodBookModel.getISBN()); }).isInstanceOf(BookNotFoundException.class);
    }

    @Test
    void testGoodAddBook() {
        mock(BookModel.class);
        mock(BookRepository.class);

        when(bookRepository.save(goodBookModel)).thenReturn(goodBookModel);
        bookService.addBook(goodBookModel);
    }
    @Test
    void testBadAddBook() {
        mock(BookModel.class);
        mock(BookRepository.class);

        when(bookRepository.save(badBookModel)).thenReturn(badBookModel);
        when(bookRepository.findById(any())).thenReturn(Optional.ofNullable(badBookModel2));

        assertThatThrownBy(() -> { bookService.addBook(badBookModel); }).isInstanceOf(InvalidISBNException.class);
        assertThatThrownBy(() -> { bookService.addBook(badBookModel2); }).isInstanceOf(BookAlreadyExistsException.class);
    }

    @Test
    void testGoodDeleteBook() {
        mock(BookModel.class);
        mock(BookRepository.class);

        doNothing().when(bookRepository).deleteById(any());
        when(bookRepository.findById(any())).thenReturn(Optional.ofNullable(goodBookModel));

        bookService.deleteBook(goodBookModel.getISBN());
    }

    @Test
    void testBadDeleteBook() {
        mock(BookModel.class);
        mock(BookRepository.class);

        doNothing().when(bookRepository).deleteById(any());
        when(bookRepository.findById(any())).thenReturn(Optional.ofNullable(null));

        assertThatThrownBy(() -> { bookService.deleteBook(badBookModel2.getISBN()); }).isInstanceOf(BookNotFoundException.class);
    }

    @Test
    void testGoodUpdateBook() {
        mock(BookModel.class);
        mock(BookRepository.class);

        when(bookRepository.save(updateBookModel)).thenReturn(updateBookModel);
        when(bookRepository.findById(any())).thenReturn(Optional.ofNullable(updateBookModel));
        bookService.updateBook(updateBookModel);
    }

    @Test
    void testBadUpdateBook() {
        mock(BookModel.class);
        mock(BookRepository.class);

        when(bookRepository.save(badBookModel)).thenReturn(badBookModel);
        when(bookRepository.findById(any())).thenReturn(Optional.ofNullable(null));

        assertThatThrownBy(() -> { bookService.updateBook(badBookModel); }).isInstanceOf(InvalidISBNException.class);
        assertThatThrownBy(() -> { bookService.updateBook(badBookModel3); }).isInstanceOf(BookNotFoundException.class);
    }
}