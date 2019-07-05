package be.vdab.fietsacademy.repositories;

import be.vdab.fietsacademy.domain.Docent;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DocentRepository
{
    Optional<Docent> findById(long id);
    void create(Docent docent);
    void delete(long id);
    List<Docent> findAll();
    List<Docent> findByWeddeBetween(BigDecimal van, BigDecimal tot);
}
