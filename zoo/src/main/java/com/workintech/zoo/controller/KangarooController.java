package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Gender;
import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.AnimalValidator;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    private Map<Integer, Kangaroo> kangarooMap ;


    @PostConstruct
    public void init () {
        kangarooMap = new HashMap<>();
        kangarooMap.put(1, new Kangaroo(1, "Lena", 35, Gender.FEMALE,false,1.6));
    }
// GETALL [GET]/workintech/kangaroos => tüm kangaroo listini dönmeli.
// GET [GET]/workintech/kangaroos/{id} => İlgili id deki kangaroo objesini dönmeli.
//SAVE [POST]/workintech/kangaroos => Bir adet kangaroo objesini kangaroos listesine ekler
// UPDATE [PUT]/workintech/kangaroos/{id} => İlgili id deki kangaroo objesinin değerlerini yeni gelen data ile değiştirir.//
//DELETE [DELETE]/workintech/developers/{id} => İlgili id değerindeki kangaroo objesini listeden siler.


    @GetMapping("/")
        public List<Kangaroo> getAll ( ) {
            return  kangarooMap.values().stream().toList();
}

    @GetMapping("/{id}")
        public Kangaroo get(@PathVariable int id) {
        AnimalValidator.isIdNotValid(id);
        AnimalValidator.isIdNotExist(kangarooMap,id);
        return kangarooMap.get(id);
}

    @PostMapping("/")
        public Kangaroo save(@RequestBody Kangaroo kangaroo){
        AnimalValidator.isIdNotValid(kangaroo.getId());
        AnimalValidator.isIdExist(kangarooMap,kangaroo.getId());
        AnimalValidator.isAnimalValid(kangaroo);
        AnimalValidator.isKangarooValid(kangaroo);
        kangarooMap.put(kangaroo.getId(), kangaroo);
        return kangarooMap.get(kangaroo.getId());
}

    @PutMapping("{id}")
        public  Kangaroo update (@PathVariable int id , @RequestBody Kangaroo kangaroo){
        AnimalValidator.isIdNotValid(kangaroo.getId());
        AnimalValidator.isIdNotExist(kangarooMap, kangaroo.getId());
        AnimalValidator.isAnimalValid(kangaroo);
        AnimalValidator.isKangarooValid(kangaroo);
        kangaroo.setId(id);
        kangarooMap.put(id,kangaroo);
        return  kangaroo ;

    }


    @DeleteMapping("/{id}")
        public Kangaroo delete (@PathVariable int id) {
        AnimalValidator.isIdNotValid(id);
        AnimalValidator.isIdNotExist(kangarooMap,id);
        Kangaroo kangaroo = kangarooMap.get(id);
        kangarooMap.remove(id);
        return  kangaroo ;

    }




}
