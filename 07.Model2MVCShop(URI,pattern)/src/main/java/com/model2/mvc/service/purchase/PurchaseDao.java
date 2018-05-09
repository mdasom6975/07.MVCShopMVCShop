package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

//==> �ǸŰ������� CRUD �߻�ȭ/ĸ��ȭ�� DAO Interface Definition
public interface PurchaseDao {
	
	// INSERT
	public void addPurchase(Purchase purchase) throws Exception;
	
	// SELECT ONE
	public Purchase getPurchase(int tranNo) throws Exception;
	
	// SELECT ONE
	public Purchase getPurchase2(int prodNo) throws Exception;
	
	// SELECT LIST
	public List<Purchase> getPurchseList(Search search, String buyerId) throws Exception;
	
	// UPDATE
	public void updatePurchase(Purchase purchase) throws Exception;
	
	// UPDATE
	public void updateTranCode(Purchase purchase) throws Exception;
	
	// �Խ��� Page ó���� ���� ��üRow(totalCount)  return
	public int getTotalCount(Search search, String buyerId) throws Exception ;
	
} //end of PurchaseDAO
