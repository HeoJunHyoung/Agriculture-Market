package market.agriculture.entity;

import market.agriculture.exception.custom.NotEnoughStockException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ItemTest {

    @DisplayName("판매 물품 생성")
    @Test
    public void testCreateItem() {
        // given
        String itemName = "사과";
        Long weight = 10L;
        Long stockQuantity = 100L;
        int price = 1000;

        // when
        Item item = Item.createItem(itemName, weight, stockQuantity, price);

        // then
        assertThat(item.getItemName()).isEqualTo(itemName);
        assertThat(item.getWeight()).isEqualTo(weight);
        assertThat(item.getStockQuantity()).isEqualTo(stockQuantity);
        assertThat(item.getPrice()).isEqualTo(price);
    }

    @DisplayName("물품 수량 추가 메서드 테스트")
    @Test
    public void testAddStockQuantity() {
        //given
        Item item = new Item();
        item.setStockQuantity(10L);

        //when
        item.addStockQuantity(5);

        //tthen
        assertThat(item.getStockQuantity()).isEqualTo(15);
    }

    @DisplayName("물품 수량 감소 메서드 테스트")
    @Test
    public void testReduceStockQuantity() {
        // given
        Item item = new Item();
        item.setStockQuantity(10L);

        // when
        item.reduceStockQuantity(3);

        // then
        assertThat(item.getStockQuantity()).isEqualTo(7L);
    }


    @DisplayName("물품 수량 감소 메서드 테스트 - 물품 재고 부족")
    @Test
    public void testReduceStockQuantityNotEnoughStock() {
        //given
        Item item = new Item();
        item.setStockQuantity(5L);

        //when then
        assertThatThrownBy(() -> item.reduceStockQuantity(10))
                .isInstanceOf(NotEnoughStockException.class)
                .hasMessage("재고가 부족합니다.");
    }
}