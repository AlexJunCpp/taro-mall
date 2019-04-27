package org.rxjava.service.goods.services;

import org.rxjava.service.goods.entity.Goods;
import org.rxjava.service.goods.form.GoodsCreateForm;
import org.rxjava.service.goods.form.GoodsListForm;
import org.rxjava.service.goods.form.GoodsPageForm;
import org.rxjava.service.goods.model.GoodsModel;
import org.rxjava.service.goods.repository.GoodsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author happy 2019-03-23 00:16
 * 商品服务
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsRepository goodsRepository;

    public Flux<GoodsModel> getListModel(Pageable pageable, GoodsListForm form) {
        return goodsRepository
                .getList(pageable, form)
                .map(this::transform);
    }

    public Mono<Page<Goods>> getPage(Pageable pageable, GoodsPageForm form) {
        return goodsRepository
                .findPage(pageable, form);
    }

    public Mono<GoodsModel> getByGoodsId(String goodsId) {
        return Mono.empty();
    }

    private GoodsModel transform(Goods goods) {
        GoodsModel model = new GoodsModel();
        BeanUtils.copyProperties(goods, model);
        return model;
    }

    public Mono<Goods> create(GoodsCreateForm form) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(form, goods);
        return goodsRepository
                .save(goods);
    }
}
