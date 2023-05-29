package core.basesyntax.strategy.impl;

import core.basesyntax.dao.ProductDaoImpl;
import core.basesyntax.exception.InvalidOperatioExeption;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationAnalysis;

public class Return implements OperationAnalysis {
    private ProductDaoImpl productDao;

    public Return(ProductDaoImpl productDao) {
        this.productDao = productDao;
    }

    @Override
    public void processing(FruitTransaction fruitTransaction) {
        int currentQuantity = productDao.calculateAmount(fruitTransaction);
        int newQuantity = currentQuantity + fruitTransaction.getQuantity();
        if (newQuantity < 0) {
            throw new InvalidOperatioExeption("Result can`t be less than 0, but was: "
                    + newQuantity);
        }
        productDao.update(fruitTransaction, newQuantity);
    }
}
