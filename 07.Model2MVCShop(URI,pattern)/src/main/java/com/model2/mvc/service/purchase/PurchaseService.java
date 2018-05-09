package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

//==> �ǸŰ������� ������ ���� �߻�ȭ/ĸ��ȭ�� Service  Interface Definition  
public interface PurchaseService {
	
		// ����/�Ǹ�
		public void addPurchase(Purchase purchase) throws Exception;
		
		// ��������Ȯ��
		public Purchase getPurchase(int tranNo) throws Exception;
		
		// ��������Ȯ��
		public Purchase getPurchase2(int prodNo) throws Exception;
		
		// ������������Ʈ
		//public List<Purchase> getPurchseList(Search search) throws Exception;
		public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception;
		
		// ������������
		public void updatePurchase(Purchase purchase) throws Exception;
		
		// ����/�Ǹ� ���¼���
		public void updateTranCode(Purchase purchase) throws Exception;

}
