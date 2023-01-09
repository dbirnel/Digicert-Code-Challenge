package com.digicert.coding.controller;

import com.digicert.coding.model.BookModel;
import com.digicert.coding.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    BookModel book1;
    BookModel book2;
    BookModel badBook;
    List<BookModel> bookList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        book1 = new BookModel("00000", "Alice in Wonderland", "Alice lives an ordinary life, until the day she follows the White Rabbit down, down, down a rabbit hole. She suddenly finds herself in an enchanted world, surrounded by zany creatures like the Mad Hatter, the Duchess, and the Cheshire Cat. Alice is delighted to find that nothing in Wonderland is the least bit ordinary.", "Lewis Carroll", "Fantasy", "1865", 19.99);
        book2 = new BookModel("11111", "The Lord of the Rings", "The Lord of the Rings tells of the great quest undertaken by Frodo and the Fellowship of the Ring: Gandalf the Wizard; the hobbits Merry, Pippin, and Sam; Gimli the Dwarf; Legolas the Elf; Boromir of Gondor; and a tall, mysterious stranger called Strider.", "J. R. R. Tolkien", "Fantasy", "1954", 28.99);
        badBook = new BookModel(null, null, null, null, null, null, -10);
        bookList.add(book1);
        bookList.add(book2);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testGetAllBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(bookList);
        this.mockMvc.perform(get("/getAllBooks")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetBook() throws Exception {
        when(bookService.getBook("1")).thenReturn(book1);
        this.mockMvc.perform(get("/getBook/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testAddBook() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(book1);

        doNothing().when(bookService).addBook(book1);
        this.mockMvc.perform(post("/addBook").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status().isAccepted());
    }

    @Test
    void testBadAddBook() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(badBook);

        doNothing().when(bookService).addBook(badBook);
        this.mockMvc.perform(post("/addBook").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteBook() throws Exception {
        doNothing().when(bookService).deleteBook("1");
        this.mockMvc.perform(delete("/deleteBook/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdateBook() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(book1);

        doNothing().when(bookService).updateBook(book1);
        this.mockMvc.perform(put("/updateBook").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testBadUpdateBook() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(badBook);

        doNothing().when(bookService).updateBook(badBook);
        this.mockMvc.perform(put("/updateBook").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status().isBadRequest());
    }
}