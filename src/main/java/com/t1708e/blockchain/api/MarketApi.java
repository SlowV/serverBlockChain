package com.t1708e.blockchain.api;

import com.t1708e.blockchain.dto.CoinDTO;
import com.t1708e.blockchain.dto.MarketDTO;
import com.t1708e.blockchain.entity.Coin;
import com.t1708e.blockchain.entity.Market;
import com.t1708e.blockchain.entity.rest.RESTPagination;
import com.t1708e.blockchain.entity.rest.RESTResponse;
import com.t1708e.blockchain.service.MarketService;
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
@RequestMapping(value = "api/v1/markets")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MarketApi {
    @Autowired
    private MarketService marketService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getList(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit) {
        Specification specification = Specification.where(null);
        if (keyword != null) {
            specification = specification.and(new CoinSpecification(new SearchCriteria("name", ":", keyword)))
                    .or(new CoinSpecification(new SearchCriteria("description", ":", keyword)));
        }
        Page<Market> marketPage = marketService.getAll(specification, page, limit);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage(MessageUtil.getMsg(MessageUtil.lang_vn, MessageUtil.SUCCESS))
                .setDatas(marketPage.getContent().stream().map(x -> new MarketDTO(x)).collect(Collectors.toList()))
                .setPagination(new RESTPagination(page, limit, marketPage.getTotalPages(), marketPage.getTotalElements()))
                , HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable String id) {
        Market market = marketService.getById(id);
        if (market == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage(MessageUtil.getMsg(MessageUtil.lang_vn, MessageUtil.NOT_FOUND))
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage(MessageUtil.getMsg(MessageUtil.lang_vn, MessageUtil.SUCCESS))
                .setData(new MarketDTO(marketService.getById(id)))
                .build(),
                HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> store(@RequestBody Market market) {
        // validate.
        System.out.println(market.getName());
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage(MessageUtil.getMsg(MessageUtil.lang_vn, MessageUtil.SUCCESS))
                .setData(new MarketDTO(marketService.create(market)))
                .build(),
                HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody Market updateMarket) {
        Market existMarket = marketService.getById(id);
        if (existMarket == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage(MessageUtil.getMsg(MessageUtil.lang_vn, MessageUtil.NOT_FOUND))
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        existMarket.setName(updateMarket.getName());
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Success")
                .setData(new MarketDTO(marketService.update(existMarket)))
                .build(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        Market existMarket = marketService.getById(id);
        if (existMarket == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage(MessageUtil.getMsg(MessageUtil.lang_vn, MessageUtil.NOT_FOUND))
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        marketService.delete(existMarket);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage(MessageUtil.getMsg(MessageUtil.lang_vn, MessageUtil.SUCCESS))
                .build(),
                HttpStatus.OK);
    }
}
