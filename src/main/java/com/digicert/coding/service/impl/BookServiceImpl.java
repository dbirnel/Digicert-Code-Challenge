package com.digicert.coding.service.impl;

import com.digicert.coding.exception.BookAlreadyExistsException;
import com.digicert.coding.exception.BookNotFoundException;
import com.digicert.coding.exception.InvalidISBNException;
import com.digicert.coding.model.BookModel;
import com.digicert.coding.repository.BookRepository;
import com.digicert.coding.service.BookService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        PopulateDatabase();
    }

    public void PopulateDatabase() {
        bookRepository.save(new BookModel("00000", "Alice in Wonderland", "Alice lives an ordinary life, until the day she follows the White Rabbit down, down, down a rabbit hole. She suddenly finds herself in an enchanted world, surrounded by zany creatures like the Mad Hatter, the Duchess, and the Cheshire Cat. Alice is delighted to find that nothing in Wonderland is the least bit ordinary.", "Lewis Carroll", "Fantasy", "1865", 19.99));
        bookRepository.save(new BookModel("11111", "The Lord of the Rings", "The Lord of the Rings tells of the great quest undertaken by Frodo and the Fellowship of the Ring: Gandalf the Wizard; the hobbits Merry, Pippin, and Sam; Gimli the Dwarf; Legolas the Elf; Boromir of Gondor; and a tall, mysterious stranger called Strider.", "J. R. R. Tolkien", "Fantasy", "1954", 28.99));
        bookRepository.save(new BookModel("22222", "Pride and Prejudice", "Since its immediate success in 1813, Pride and Prejudice has remained one of the most popular novels in the English language. Jane Austen called this brilliant work \"her own darling child\" and its vivacious heroine, Elizabeth Bennet, \"as delightful a creature as ever appeared in print.\" The romantic clash between the opinionated Elizabeth and her proud beau, Mr. Darcy, is a splendid performance of civilized sparring. And Jane Austen's radiant wit sparkles as her characters dance a delicate quadrille of flirtation and intrigue, making this book the most superb comedy of manners of Regency England.", "Jane Austen", "Romance", "1813", 47.99));
        bookRepository.save(new BookModel("33333", "The Hitchhiker's Guide to the Galaxy", "Seconds before the Earth is demolished to make way for a galactic freeway, Arthur Dent is plucked off the planet by his friend Ford Prefect, a researcher for the revised edition of The Hitchhiker's Guide to the Galaxy who, for the last fifteen years, has been posing as an out-of-work actor. Together this dynamic pair begin a journey through space aided by quotes from The Hitchhiker's Guide (\"A towel is about the most massively useful thing an interstellar hitchhiker can have\") and a galaxy-full of fellow travelers: Zaphod Beeblebrox--the two-headed, three-armed ex-hippie and totally out-to-lunch president of the galaxy; Trillian, Zaphod's girlfriend (formally Tricia McMillan), whom Arthur tried to pick up at a cocktail party once upon a time zone; Marvin, a paranoid, brilliant, and chronically depressed robot; Veet Voojagig, a former graduate student who is obsessed with the disappearance of all the ballpoint pens he bought over the years. Where are these pens? Why are we born? Why do we die? Why do we spend so much time between wearing digital watches? For all the answers stick your thumb to the stars. And don't forget to bring a towel!", "Douglas Adams", "Science Fiction", "1979", 9.99));
        bookRepository.save(new BookModel("44444", "Dracula", "When Jonathan Harker visits Transylvania to help Count Dracula with the purchase of a London house, he makes a series of horrific discoveries about his client. Soon afterwards, various bizarre incidents unfold in England: an apparently unmanned ship is wrecked off the coast of Whitby; a young woman discovers strange puncture marks on her neck; and the inmate of a lunatic asylum raves about the 'Master' and his imminent arrival.", "Bram Stoker", "Horror", "1897", 15.99));
        bookRepository.save(new BookModel("55555", "A Christmas Carol", "To bitter, miserly Ebenezer Scrooge, Christmas is just another day. But all that changes when the ghost of his long-dead business partner appears, warning Scrooge to change his ways before it's too late.", "Charles Dickens", "Fiction", "1843", 12.99));
        bookRepository.save(new BookModel("66666", "The Count of Monte Cristo", "Thrown in prison for a crime he has not committed, Edmond Dantès is confined to the grim fortress of If. There he learns of a great hoard of treasure hidden on the Isle of Monte Cristo and he becomes determined not only to escape, but also to unearth the treasure and use it to plot the destruction of the three men responsible for his incarceration. Dumas’ epic tale of suffering and retribution, inspired by a real-life case of wrongful imprisonment, was a huge popular success when it was first serialized in the 1840s.", "Alexandre Dumas", "Adventure", "1846", 24.99));
        bookRepository.save(new BookModel("77777", "Treasure Island", "For sheer storytelling delight and pure adventure, Treasure Island has never been surpassed. From the moment young Jim Hawkins first encounters the sinister Blind Pew at the Admiral Benbow Inn until the climactic battle for treasure on a tropic isle, the novel creates scenes and characters that have fired the imaginations of generations of readers. Written by a superb prose stylist, a master of both action and atmosphere, the story centers upon the conflict between good and evil - but in this case a particularly engaging form of evil. It is the villainy of that most ambiguous rogue Long John Silver that sets the tempo of this tale of treachery, greed, and daring. Designed to forever kindle a dream of high romance and distant horizons, Treasure Island is, in the words of G. K. Chesterton, 'the realization of an ideal, that which is promised in its provocative and beckoning map; a vision not only of white skeletons but also green palm trees and sapphire seas.' G. S. Fraser terms it 'an utterly original book' and goes on to write: 'There will always be a place for stories like Treasure Island that can keep boys and old men happy.'", "Robert Louis", "Adventure", "1882", 18.99));
        bookRepository.save(new BookModel("88888", "The Wonderful Wizard of Oz", "Swept away from her home in Kansas by a tornado, Dorothy and her dog Toto find themselves stranded in the fantastical Land of Oz. As instructed by the Good Witch of the North and the Munchkins, Dorothy sets off on the yellow brick road to try and find her way to the Emerald City and the Wizard of Oz, who can help her get home. With her companions the Scarecrow, the Tin Woodman and the Cowardly Lion, Dorothy experiences an adventure full of friendship, magic and danger. A much-loved children's classic, The Wizard of Oz continues to delight readers young and old with its enchanting tale of witches, flying monkeys and silver shoes.", "L. Frank Baum", "Fantasy", "1900", 99.99));
        bookRepository.save(new BookModel("99999", "Dr. Jekyll and Mr. Hyde", "Robert Louis Stevenson's masterpiece of the duality of good and evil in man's nature sprang from the darkest recesses of his own unconscious—during a nightmare from which his wife awakened him, alerted by his screams. More than a hundred years later, this tale of the mild-mannered Dr. Jekyll and the drug that unleashes his evil, inner persona—the loathsome, twisted Mr. Hyde—has lost none of its ability to shock. Its realistic police-style narrative chillingly relates Jekyll's desperation as Hyde gains control of his soul—and gives voice to our own fears of the violence and evil within us. Written before Freud's naming of the ego and the id, Stevenson's enduring classic demonstrates a remarkable understanding of the personality's inner conflicts—and remains the irresistibly terrifying stuff of our worst nightmares.", "Robert Louis Stevenson", "Horror", "1886", 68.99));
    }

    @Override
    public List<BookModel> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public BookModel getBook(String ISBN) {
        if(bookRepository.findById(ISBN).isEmpty()) {
            throw new BookNotFoundException("No book with ISBN " + ISBN + " found.");
        }
        return bookRepository.findById(ISBN).get();
    }

    @Override
    public void addBook(BookModel book) {
        if(book.getISBN() == null || book.getISBN().isEmpty()) {
            throw new InvalidISBNException("Invalid ISBN was provided");
        }
        if(bookRepository.findById(book.getISBN()).isPresent()) {
            throw new BookAlreadyExistsException("ISBN already used");
        }
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(String ISBN) {
        if(ISBN == null || ISBN.isEmpty() || bookRepository.findById(ISBN).isEmpty()) {
            throw new BookNotFoundException("No book with ISBN " + ISBN + " found.");
        }
        bookRepository.deleteById(ISBN);
    }

    @Override
    public void updateBook(BookModel book) {
        if(book.getISBN() == null || book.getISBN().isEmpty()) {
            throw new InvalidISBNException("Invalid ISBN was provided");
        }
        if(bookRepository.findById(book.getISBN()).isEmpty()) {
            throw new BookNotFoundException("No book with ISBN " + book.getISBN() + " found.");
        }
        bookRepository.save(book);
    }
}
