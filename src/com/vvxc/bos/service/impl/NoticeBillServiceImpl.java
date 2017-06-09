package com.vvxc.bos.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.jmx.snmp.Timestamp;
import com.vvxc.bos.bean.Decidedzone;
import com.vvxc.bos.bean.Noticebill;
import com.vvxc.bos.bean.Workbill;
import com.vvxc.bos.dao.IDecidezoneDao;
import com.vvxc.bos.dao.INoticeBillDao;
import com.vvxc.bos.dao.IWorkbillDao;
import com.vvxc.bos.service.INoticeBillService;


@Service
@Transactional
public class NoticeBillServiceImpl implements INoticeBillService {
	@Autowired
	private IDecidezoneDao decidezonedao;
	@Autowired
	private INoticeBillDao noticeBillDao;
	@Autowired
	private IWorkbillDao workbillDaoImpl;
	@Override
	public void save(Noticebill model,String decidedzone_id) {
		// TODO Auto-generated method stub
			noticeBillDao.save(model);
			if (decidedzone_id!=null&&!"".equals(decidedzone_id)) {
				Decidedzone decidedzone = decidezonedao.findById(decidedzone_id);
				model.setStaff(decidedzone.getStaff());
				model.setOrdertype("自动");
				Workbill workbill=new Workbill();
				workbill.setBuildtime(new Date());
				workbill.setAttachbilltimes(0);
				workbill.setPickstate("未取件");
				workbill.setRemark(model.getRemark());
				workbill.setStaff(decidedzone.getStaff());
				workbill.setType("新单");
				workbill.setNoticebill(model);
				workbillDaoImpl.save(workbill);
			}else{
				model.setOrdertype("人工");
			}
	}

}
