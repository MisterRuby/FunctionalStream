package funcDesign;

import org.junit.Test;

public class DecoratorPattern {

    class Price {
        private final Integer priceVal;

        public Price(Integer priceVal) {
            this.priceVal = priceVal;
        }
        public Integer getPriceVal() {
            return this.priceVal;
        }
    }

    @FunctionalInterface
    interface PriceProcessor {
        Price process(Price price);

        default PriceProcessor andThen(PriceProcessor nextProcessor){
            return price -> nextProcessor.process(process(price));
        }
    }

    class BasicProcessor implements PriceProcessor {
        @Override
        public Price process(Price price) {
            return price;
        }
    }

    class DiscountProcessor implements PriceProcessor {
        @Override
        public Price process(Price price) {
            return new Price(price.getPriceVal() / 10);
        }
    }

    class TaxProcessor implements PriceProcessor {
        @Override
        public Price process(Price price) {
            return new Price(price.getPriceVal() * 2);
        }
    }

    @Test
    public void testDecorateProcessor() {
        System.out.println("DecoratorPattern.testDecorateProcessor");
        Price beforePrice = new Price(10000);

        PriceProcessor basicProcessor = new BasicProcessor();
//        PriceProcessor discountProcessor = new DiscountProcessor();
//        PriceProcessor taxProcessor = new TaxProcessor();
//
//        PriceProcessor decorateProcessor = basicProcessor
//                .andThen(discountProcessor)
//                .andThen(taxProcessor);

        // 람다의 익명 클래스 구현을 통해 클래스 작성을 줄이고 간편하게 작성
        //  - 클래스로 만들어 재활용 할 것인지 람다로 코드량을 줄일 것인지는 상황에 맞게 판단
        PriceProcessor decorateProcessor = basicProcessor
                .andThen(price -> new Price(price.getPriceVal() / 10))
                .andThen(price -> new Price(price.getPriceVal() * 2));

        Price resultPrice = decorateProcessor.process(beforePrice);

        System.out.println(resultPrice.getPriceVal());
    }
}
