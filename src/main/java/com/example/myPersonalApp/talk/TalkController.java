package com.example.myPersonalApp.talk;

import com.example.myPersonalApp.enums.Categoria;
import com.example.myPersonalApp.exceptions.BadRequestException;
import com.example.myPersonalApp.payloads.entities.TalkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/talk")
public class TalkController {

    @Autowired
    TalkService talkService;

    @PostMapping("")
        @PreAuthorize("hasAuthority('Admin')")
    public Talk save(@RequestBody @Validated TalkDTO talkDTO, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return talkService.save(talkDTO);
    }

    @GetMapping("")
    public Page<Talk> getAll(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10") int size,
                             @RequestParam(defaultValue = "id") String orderBy){
        return talkService.getAllPaginated(page,size,orderBy);
    }

    @GetMapping("/categoria/{categoria}")
    public  Page<Talk> getAllByCategoria(@PathVariable String categoria,
@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10")int size,@RequestParam(defaultValue = "id") String orderBy){
        Categoria categoria1 = Categoria.valueOf(categoria);
        return talkService.findByCategoria(categoria1),page,size,orderBy;
    }
    @GetMapping("/titolo/{titolo}")
    public  Page<Talk> getAllByTitolo(@PathVariable String titolo,
@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10")int size,@RequestParam(defaultValue = "id") String orderBy){
        return talkService.findByTitolo(titolo),page,size,orderBy;
    }
    @GetMapping("/titoloAndCategoria/{titolo}/{categoria}")
    public  Page<Talk> getAllByTitoloAndCategoria(@PathVariable String titolo,@PathVariable String categoria,
@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10")int size,@RequestParam(defaultValue = "id") String orderBy){
        Categoria categoria1 = Categoria.valueOf(categoria);
        return talkService.findByTitoloAndCategoria(titolo,categoria1),page,size,orderBy;
    }
    @DeleteMapping("/{id}")
        @PreAuthorize("hasAuthority('Admin')")
    public boolean deleteById(@PathVariable long id){
        return talkService.deleteById(id);
    }

    @PutMapping("/{id}")
        @PreAuthorize("hasAuthority('Admin')")
    public Talk putById(@PathVariable long id,@RequestBody TalkDTO talkDTO){
        return talkService.putById(id,talkDTO);
    }

    @GetMapping("/{id}")
    public Talk getById(@PathVariable long id){
        return talkService.getById(id);
    }
}
