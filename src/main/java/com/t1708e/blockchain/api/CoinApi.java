package com.t1708e.blockchain.api;

import com.t1708e.blockchain.dto.CoinDTO;
import com.t1708e.blockchain.entity.Coin;
import com.t1708e.blockchain.entity.rest.RESTPagination;
import com.t1708e.blockchain.entity.rest.RESTResponse;
import com.t1708e.blockchain.service.CoinService;
import com.t1708e.blockchain.specification.CoinSpecification;
import com.t1708e.blockchain.specification.SearchCriteria;
import com.t1708e.blockchain.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/coins")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CoinApi {
    @Autowired
    private CoinService coinService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getList(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit,
            @RequestParam(value = "marketId", required = false) String marketId) {
        Specification specification = Specification.where(null);
        if (keyword != null) {
            specification = specification.and(new CoinSpecification(new SearchCriteria("name", ":", keyword)));
        }
        if (marketId != null){
            specification = specification.and(new CoinSpecification(new SearchCriteria("marketIdString", ":", marketId)));
            System.out.println(marketId);
        }
        Page<Coin> coinPage = coinService.getAll(specification, page, limit);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage(MessageUtil.getMsg(MessageUtil.lang_vn, MessageUtil.SUCCESS))
                .setData(coinPage.getContent().stream().map(x -> new CoinDTO(x)).collect(Collectors.toList()))
                .setPagination(new RESTPagination(page, limit, coinPage.getTotalPages(), coinPage.getTotalElements()))
                , HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable String id) {
        Coin coin = coinService.getById(id);
        if (coin == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage(MessageUtil.getMsg(MessageUtil.lang_vn, MessageUtil.NOT_FOUND))
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage(MessageUtil.getMsg(MessageUtil.lang_vn, MessageUtil.SUCCESS))
                .setData(new CoinDTO(coinService.getById(id)))
                .build(),
                HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> store(@RequestBody Coin coin) {
        // validate.
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage(MessageUtil.getMsg(MessageUtil.lang_vn, MessageUtil.SUCCESS))
                .setData(new CoinDTO(coinService.create(coin)))
                .build(),
                HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody Coin updateCoin) {
        Coin existCoin = coinService.getById(id);
        if (existCoin == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage(MessageUtil.getMsg(MessageUtil.lang_vn, MessageUtil.NOT_FOUND))
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        existCoin.setName(updateCoin.getName());
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Success")
                .setData(new CoinDTO(coinService.update(existCoin)))
                .build(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        Coin existCoin = coinService.getById(id);
        if (existCoin == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage(MessageUtil.getMsg(MessageUtil.lang_vn, MessageUtil.NOT_FOUND))
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        coinService.delete(existCoin);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage(MessageUtil.getMsg(MessageUtil.lang_vn, MessageUtil.SUCCESS))
                .build(),
                HttpStatus.OK);
    }

}
