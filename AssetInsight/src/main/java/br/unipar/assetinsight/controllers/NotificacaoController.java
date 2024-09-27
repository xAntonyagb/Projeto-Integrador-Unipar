package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.responses.NotificacaoResponse;
import br.unipar.assetinsight.entities.NotificacaoEntity;
import br.unipar.assetinsight.mappers.NotificacaoMapper;
import br.unipar.assetinsight.service.NotificacaoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("notificacao")
public class NotificacaoController {
    private final NotificacaoService notificacaoService;

    @GetMapping("/all")
    public ResponseEntity<Page<NotificacaoResponse>> getAll(Pageable pageable) {
        Page<NotificacaoEntity> retorno = notificacaoService.getAll(pageable);
        Page<NotificacaoResponse> response = NotificacaoMapper.INSTANCE.toResponsePage(retorno);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<NotificacaoResponse> getById(long id) {
        NotificacaoEntity retorno = notificacaoService.getById(id);
        NotificacaoResponse response = NotificacaoMapper.INSTANCE.toResponse(retorno);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/read")
    public ResponseEntity<Void> read(@RequestParam long id) {
        notificacaoService.lerNotificao(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/read/all")
    public ResponseEntity<Void> readAll() {
        notificacaoService.lerTodasNotificacoes();
        return ResponseEntity.ok().build();
    }

}
