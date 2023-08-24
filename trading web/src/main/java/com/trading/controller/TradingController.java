package com.trading.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trading.dao.TradingNewsDao;
import com.trading.dto.TradingNewsDto;

@Controller
@RequestMapping("/trading")

public class TradingController {

	TradingNewsDao tradingnewsDao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public TradingController(TradingNewsDao tradingnewsDao) {
		this.tradingnewsDao = tradingnewsDao;
	}

//  메인 화면
	@GetMapping("/main")
	public String main(Model model) {
		List<TradingNewsDto> jusikh;
		List<TradingNewsDto> jusiks;
		List<TradingNewsDto> jusikm;
		List<TradingNewsDto> jusikt;
		List<TradingNewsDto> dealo;
		List<TradingNewsDto> deal;
	    try {
	    	jusikh = tradingnewsDao.jusikh();
	    	jusiks = tradingnewsDao.jusiks();
	    	jusikm = tradingnewsDao.jusikm();
	    	jusikt = tradingnewsDao.jusikt();
	    	dealo = tradingnewsDao.dealo();
	    	deal = tradingnewsDao.deal();
	        model.addAttribute("jusikh", jusikh);
	        model.addAttribute("jusiks", jusiks);
	        model.addAttribute("jusikm", jusikm);
	        model.addAttribute("jusikt", jusikt);
	        model.addAttribute("dealo", dealo);
	        model.addAttribute("deal", deal);
	    } catch (Exception e) {
	        e.printStackTrace();
	        logger.warn("목록 생성 과정에서 문제 발생");
	        model.addAttribute("error", "목록이 정상적으로 처리되지 않았습니다.");
	    }
		return "dashboard-analytics";
	}

//	뉴스 화면
	@GetMapping("/news")
	public String getItemsByPage(@RequestParam(defaultValue = "1") int page, Model model) {
	    int pageSize = 100; // 한 페이지에 보여줄 아이템 수
	    List<TradingNewsDto> list;
	    try {
	        list = tradingnewsDao.searchAll();
	        int listSize = list.size();

	        // Calculate the start and end indices for pagination
	        int startIndex = (page - 1) * pageSize;
	        int endIndex = Math.min(startIndex + pageSize, listSize);

	        // Get the sublist based on the calculated indices
	        List<TradingNewsDto> items = list.subList(startIndex, endIndex);
	        model.addAttribute("list", list);
	        model.addAttribute("items", items);
	        model.addAttribute("currentPage", page);
	        model.addAttribute("totalPages", (int) Math.ceil((double) listSize / pageSize));
	    } catch (Exception e) {
	        e.printStackTrace();
	        logger.warn("목록 생성 과정에서 문제 발생");
	        model.addAttribute("error", "목록이 정상적으로 처리되지 않았습니다.");
	    }
	    return "page-orders";
	}
	
//  코스피 데이터
	@RequestMapping("/kospi")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> kospi() {
		List<Map<String, Object>> kospi;
	    try {
	        kospi = tradingnewsDao.kospit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        logger.warn("목록 생성 과정에서 문제 발생");
	        return null; // 에러 발생 시 null 반환
	    }
		return kospi; // 데이터 반환
	}
	
//  환율 화면
	@GetMapping("/exchange")
	public String exchange() {
		return "chart-exchange";
	} //exchange end

	
//  환율 데이터
	@RequestMapping("/exchange_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> exchange_data() {
		List<Map<String, Object>> exchange;
	    try {
	    	exchange = tradingnewsDao.exchanget();
	    } catch (Exception e) {
	        e.printStackTrace();
	        logger.warn("목록 생성 과정에서 문제 발생");
	        return null; // 에러 발생 시 null 반환
	    }
		return exchange; // 데이터 반환
	}
	
//  KT&G 화면
	@GetMapping("/033780")
	public String charta() {
		return "chart-033780";
	} //exchange end
	
//  KT&G 데이터
	@RequestMapping("/033780_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> charta_data() {
		List<Map<String, Object>> jusik;
	    try {
	    	jusik = tradingnewsDao.charta();
	    } catch (Exception e) {
	        e.printStackTrace();
	        logger.warn("목록 생성 과정에서 문제 발생");
	        return null; // 에러 발생 시 null 반환
	    }
		return jusik; // 데이터 반환
	}
	
//  현대로템 화면
	@GetMapping("/064350")
	public String chartb() {
		return "chart-064350";
	} //exchange end
	
//  현대로템 데이터
	@RequestMapping("/064350_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartb_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartb();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  SK텔레콤 화면
	@GetMapping("/017670")
	public String chartc() {
		return "chart-017670";
	} //exchange end
	
//  SK텔레콤 데이터
	@RequestMapping("/017670_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartc_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartc();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  POSCO홀딩스 화면
	@GetMapping("/005490")
	public String chartd() {
		return "chart-005490";
	} //exchange end
	
//  POSCO홀딩스 데이터
	@RequestMapping("/005490_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartd_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartd();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  한솔케미칼  화면
	@GetMapping("/014680")
	public String charte() {
		return "chart-014680";
	} //exchange end
	
//  한솔케미칼  데이터
	@RequestMapping("/014680_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> charte_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.charte();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
// 한화에어로스페이스 화면
	@GetMapping("/012450")
	public String chartf() {
		return "chart-012450";
	} //exchange end
	
//  한화에어로스페이스 데이터
	@RequestMapping("/012450_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartf_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartf();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  LG생활건강 화면
	@GetMapping("/051900")
	public String chartg() {
		return "chart-051900";
	} //exchange end
	
//  LG생활건강 데이터
	@RequestMapping("/051900_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartg_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartg();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  삼성전기 화면
	@GetMapping("/009150")
	public String charth() {
		return "chart-009150";
	} //exchange end
	
//  삼성전기 데이터
	@RequestMapping("/009150_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> charth_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.charth();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  LG전자 화면
	@GetMapping("/066570")
	public String charti() {
		return "chart-066570";
	} //exchange end
	
//  LG전자 데이터
	@RequestMapping("/066570_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> charti_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.charti();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  LX세미콘 화면
	@GetMapping("/108320")
	public String chartj() {
		return "chart-108320";
	} //exchange end
	
//  LX세미콘 데이터
	@RequestMapping("/108320_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartj_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartj();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  한국항공우주 화면
	@GetMapping("/047810")
	public String chartk() {
		return "chart-047810";
	} //exchange end
	
//  한국항공우주 데이터
	@RequestMapping("/047810_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartk_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartk();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  SK하이닉스 화면
	@GetMapping("/000660")
	public String chartl() {
		return "chart-000660";
	} //exchange end
	
//  SK하이닉스 데이터
	@RequestMapping("/000660_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartl_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartl();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  삼성중공업 화면
	@GetMapping("/010140")
	public String chartm() {
		return "chart-010140";
	} //exchange end
	
//  삼성중공업 데이터
	@RequestMapping("/010140_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartm_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartm();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  NAVER 화면
	@GetMapping("/035420")
	public String chartn() {
		return "chart-035420";
	} //exchange end
	
//  NAVER 데이터
	@RequestMapping("/035420_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartn_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartn();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  현대차 화면
	@GetMapping("/005380")
	public String charto() {
		return "chart-005380";
	} //exchange end
	
//  현대차 데이터
	@RequestMapping("/005380_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> charto_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.charto();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  한화솔루션 화면
	@GetMapping("/009830")
	public String chartp() {
		return "chart-009830";
	} //exchange end
	
//  한화솔루션 데이터
	@RequestMapping("/009830_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartp_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartp();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  한전기술 화면
	@GetMapping("/052690")
	public String chartq() {
		return "chart-033780";
	} //exchange end
	
//  한전기술 데이터
	@RequestMapping("/052690_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartq_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartq();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  두산에너빌리티 화면
	@GetMapping("/034020")
	public String chartr() {
		return "chart-034020";
	} //exchange end
	
//  두산에너빌리티 데이터
	@RequestMapping("/034020_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartr_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartr();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  코스모신소재 화면
	@GetMapping("/005070")
	public String charts() {
		return "chart-005070";
	} //exchange end
	
//  코스모신소재 데이터
	@RequestMapping("/005070_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> charts_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.charts();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
// 코스모화학 화면
	@GetMapping("/005420")
	public String chartt() {
		return "chart-005420";
	} //exchange end
	
//  코스모화학 데이터
	@RequestMapping("/005420_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartt_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartt();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  한미반도체 화면
	@GetMapping("/042700")
	public String chartu() {
		return "chart-042700";
	} //exchange end
	
//  한미반도체 데이터
	@RequestMapping("/042700_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartu_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartu();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  삼성엔지니어링 화면
	@GetMapping("/028050")
	public String chartv() {
		return "chart-028050";
	} //exchange end
	
//  삼성엔지니어링 데이터
	@RequestMapping("/028050_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartv_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartv();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  셀트리온 화면
	@GetMapping("/068270")
	public String chartw() {
		return "chart-068270";
	} //exchange end
	
//  셀트리온 데이터
	@RequestMapping("/068270_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartw_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartw();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  에스디바이오센서 화면
	@GetMapping("/137310")
	public String chartx() {
		return "chart-137310";
	} //exchange end
	
//  에스디바이오센서 데이터
	@RequestMapping("/137310_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartx_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartx();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  삼성전자 화면
	@GetMapping("/005930")
	public String charty() {
		return "chart-005930";
	} //exchange end
	
//  삼성전자 데이터
	@RequestMapping("/005930_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> charty_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.charty();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  한국카본 화면
	@GetMapping("/017960")
	public String chartz() {
		return "chart-017960";
	} //exchange end
	
//  한국카본 데이터
	@RequestMapping("/017960_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartz_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartz();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  세진중공업 화면
	@GetMapping("/075580")
	public String chartaa() {
		return "chart-075580";
	} //exchange end
	
//  세진중공업 데이터
	@RequestMapping("/075580_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartaa_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartaa();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  대한항공 화면
	@GetMapping("/003490")
	public String chartbb() {
		return "chart-003490";
	} //exchange end
	
//  대한항공 데이터
	@RequestMapping("/003490_data")
	@ResponseBody // JSON 형식으로 데이터 반환
	public List<Map<String, Object>> chartbb_data() {
		List<Map<String, Object>> jusik;
		try {
			jusik = tradingnewsDao.chartbb();
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("목록 생성 과정에서 문제 발생");
			return null; // 에러 발생 시 null 반환
		}
		return jusik; // 데이터 반환
	}
	
//  뉴스상세 화면
	@GetMapping("/newscontent")
	public String test(@RequestParam("number") Integer number, Model model) {
		 List<TradingNewsDto> list;
		 try {
			list = tradingnewsDao.getContents(number);
	        model.addAttribute("list", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "page-invoice";
	} //exchange end
}
