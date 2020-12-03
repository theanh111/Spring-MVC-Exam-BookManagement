package controller;

import model.Book;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.BookService;
import service.CategoryService;

import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> listCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/book")
    public ModelAndView listBooks(@RequestParam("searchBook") Optional<String> searchName) {
        Iterable<Book> books;
        if (searchName.isPresent()) {
            books = bookService.findAllByNameContaining(searchName.get());
        } else {
            books = bookService.findAll();
        }
        ModelAndView modelAndView = new ModelAndView("book/list");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @GetMapping("/createNewBook")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("book/create");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    @PostMapping("/createNewBook")
    public ModelAndView createNewBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("book/create");
        modelAndView.addObject("book", new Book());
        modelAndView.addObject("message", "Created New Book!");
        return modelAndView;
    }

    @GetMapping("/editBook/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id) {
        Book book = bookService.findById(id);
        ModelAndView modelAndView = new ModelAndView("book/edit");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @PostMapping("/editBook")
    public ModelAndView editBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("book/edit");
        modelAndView.addObject("book", book);
        modelAndView.addObject("message", "Edited Book!");
        return modelAndView;
    }

    @GetMapping("/deleteBook/{id}")
    public ModelAndView showDeleteForm(@PathVariable Integer id) {
        Book book = bookService.findById(id);
        ModelAndView modelAndView = new ModelAndView("book/delete");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@ModelAttribute("book") Book book, RedirectAttributes redirect) {
        bookService.delete(book.getId());
        redirect.addFlashAttribute("message", "Deleted Book!");
        return "redirect:/book";
    }
}
