package com.example.myPersonalApp.richiesteTalk;

import com.example.myPersonalApp.enums.Categoria;
import com.example.myPersonalApp.exceptions.BadRequestException;
import com.example.myPersonalApp.payloads.entities.RichiesteTalkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/richiesteTalk")
public class RichiesteTalkController {
@Autowired
    RichiesteTalkService richiesteTalkService;


@PostMapping("")
    @PreAuthorize("hasAuthority('Admin')")
    public RichiesteTalk save (@RequestBody @Validated RichiesteTalkDTO richiesteTalkDTO, BindingResult validation){
    if(validation.hasErrors()){
        throw new BadRequestException(validation.getAllErrors());
    }
    return richiesteTalkService.save(richiesteTalkDTO);
}
@GetMapping("")
    @PreAuthorize("hasAuthority('Admin')")
    public Page<RichiesteTalk> getAllPaginated(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10") int size,
                                               @RequestParam(defaultValue = "id") String orderBy){
    return richiesteTalkService.getAllPaginated(page,size,orderBy);
}

@GetMapping("/categoria")
    @PreAuthorize("hasAuthority('Admin')")
    public Page<RichiesteTalk> getAllPaginatedByCategory(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10") int size,
                                                         @RequestParam(defaultValue = "id") String orderBy,@RequestParam(defaultValue = "Fitness") String categoria){
    return richiesteTalkService.getAllPaginatedByCategory(page,size,orderBy, Categoria.valueOf(categoria));
}

@GetMapping("/leggi/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public RichiesteTalk leggi(@PathVariable long id){
    return richiesteTalkService.leggi(id);
}
}
