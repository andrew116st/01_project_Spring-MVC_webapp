package ru.andrew116st.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.andrew116st.springcourse.models.Books;
import ru.andrew116st.springcourse.models.Person;


import java.util.List;
import java.util.Optional;


@Component
public class BooksDAO {

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public BooksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Books> index() {
        return jdbcTemplate.query("SELECT * FROM Books", new BeanPropertyRowMapper<>(Books.class));
    }

    public Books show(int id) {
        return jdbcTemplate.query("SELECT * FROM Books WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Books.class))
                .stream().findAny().orElse(null);

    }

    public void save(Books books) {
        jdbcTemplate.update("INSERT INTO Books(name, author, year) VALUES(?, ?, ?)", books.getName(), books.getAuthor(), books.getYear());
    }

    public void update(int id, Books updatedBooks) {
        jdbcTemplate.update("UPDATE Books SET name=?, author=?, year=? WHERE id=?", updatedBooks.getName(), updatedBooks.getAuthor(),
                updatedBooks.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Books WHERE id=?", id);
    }

    public List<Books> showPerson(int person_id) {
        return jdbcTemplate.query("SELECT * FROM Books WHERE person_id=?",
                new Object[]{person_id}, new BeanPropertyRowMapper<>(Books.class));
    }

    public Optional<Person> whoGrabBook(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM books JOIN person  on books.person_id = person.id " +
                        "where books.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }


    public void  clearPersonBook(int id){
         jdbcTemplate.update("UPDATE books SET person_id = null where id=?", id);
    }

    public void indexPersonBook(int idBook,int idPerson){
        jdbcTemplate.update("UPDATE books SET person_id = ? where id=?", idPerson, idBook);


    }

}

