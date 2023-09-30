package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Gender;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.AnimalValidator;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private Map<Integer, Koala> koalaMap ;


    public void init() {

        koalaMap = new HashMap<>();
        koalaMap.put(1, new Koala(1, "George", 4, Gender.MALE, 20));
    }
    // GETALL  [GET]/workintech/koalas => tüm koala listini dönmeli.
    // GET [GET]/workintech/koalas/{id} => İlgili id deki koala objesini dönmeli.
    // SAVE [POST]/workintech/koalas => Bir adet koala objesini koala listesine ekler
    // UPDATE [PUT]/workintech/koalas/{id} => İlgili id deki koala objesinin değerlerini yeni gelen data ile değiştirir.
    // DELETE [DELETE]/workintech/koalas/{id} => İlgili id değerindeki koala objesini listeden siler.


    @GetMapping("/")
        public List<Koala> getAll () {
            return koalaMap.values().stream().toList();

    }

    @GetMapping("/{id}")
        public Koala get (@PathVariable int id) {
        AnimalValidator.isIdNotExist(koalaMap,id);
        AnimalValidator.isIdNotValid(id);
        return koalaMap.get(id);

    }

    @PostMapping("/")
        public Koala save (@RequestBody Koala koala) {
            AnimalValidator.isIdNotValid(koala.getId());
            AnimalValidator.isAnimalValid(koala);
            AnimalValidator.isKoalaValid(koala);
            koalaMap.put(koala.getId(), koala);
            return koalaMap.get(koala.getId());
    }

    @PutMapping("{id]")
        public  Koala update(@PathVariable int id , @RequestBody Koala koala) {
        AnimalValidator.isIdNotValid(id);
        AnimalValidator.isIdNotExist(koalaMap,id);
        AnimalValidator.isAnimalValid(koala);
        AnimalValidator.isKoalaValid(koala);
        koalaMap.put(id,koala);
        return koalaMap.get(koala.getId());

    }

    @DeleteMapping("/{id}")
    public Koala delete(@PathVariable int id) {
        AnimalValidator.isIdNotValid(id);
        AnimalValidator.isIdExist(koalaMap,id);
        Koala deleted = koalaMap.get(id);
        koalaMap.remove(id);
        return deleted;

    }
}
