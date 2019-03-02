package cn.hu.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hu.common.annotation.Log;
import cn.hu.common.domain.ResponseBo;
import cn.hu.common.domain.Tree;
import cn.hu.common.util.FileUtil;
import cn.hu.system.domain.GoodsCategory;
import cn.hu.system.service.GoodsCategoryService;

/**
 * 商品分类
 */
@Controller
public class GoodsCategoryController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GoodsCategoryService goodsCategoryService;

	@Log("获取商品分类信息")
	@RequestMapping("goodscategory")
	@RequiresPermissions("goodscategory:list")
	public String index() {
		return "system/goodscategory/goodscategory";
	}

	@RequestMapping("goodscategory/tree")
	@ResponseBody
	public ResponseBo getDeptTree() {
		try {
			Tree<GoodsCategory> tree = this.goodsCategoryService.getGoodsCategoryTree();
			return ResponseBo.ok(tree);
		} catch (Exception e) {
			log.error("获取商品分类树失败", e);
			return ResponseBo.error("获取商品分类树失败！");
		}
	}

	@RequestMapping("goodscategory/getDept")
	@ResponseBody
	public ResponseBo getDept(Long id) {
		try {
			GoodsCategory category = this.goodsCategoryService.selectById(id);
			return ResponseBo.ok(category);
		} catch (Exception e) {
			log.error("获取商品分类信息失败", e);
			return ResponseBo.error("获取商品分类信息失败，请联系网站管理员！");
		}
	}

	@RequestMapping("goodscategory/list")
	@RequiresPermissions("goodscategory:list")
	@ResponseBody
	public List<GoodsCategory> deptList(GoodsCategory category) {
		try {
			return this.goodsCategoryService.findAllGoodsCategorys(category);
		} catch (Exception e) {
			log.error("获取商品分类列表失败", e);
			return new ArrayList<>();
		}
	}

	@RequestMapping("goodscategory/excel")
	@ResponseBody
	public ResponseBo deptExcel(GoodsCategory category) {
		try {
			List<GoodsCategory> list = this.goodsCategoryService.findAllGoodsCategorys(category);
			return FileUtil.createExcelByPOIKit("商品分类表", list, GoodsCategory.class);
		} catch (Exception e) {
			log.error("导出商品分类信息Excel失败", e);
			return ResponseBo.error("导出Excel失败，请联系网站管理员！");
		}
	}

	@RequestMapping("goodscategory/csv")
	@ResponseBody
	public ResponseBo deptCsv(GoodsCategory category) {
		try {
			List<GoodsCategory> list = this.goodsCategoryService.findAllGoodsCategorys(category);
			return FileUtil.createCsv("商品分类表", list, GoodsCategory.class);
		} catch (Exception e) {
			log.error("获取商品分类信息Csv失败", e);
			return ResponseBo.error("导出Csv失败，请联系网站管理员！");
		}
	}

	@RequestMapping("goodscategory/checkDeptName")
	@ResponseBody
	public boolean checkDeptName(String deptName, String oldDeptName) {
		if (StringUtils.isNotBlank(oldDeptName) && deptName.equalsIgnoreCase(oldDeptName)) {
			return true;
		}
		GoodsCategory result = this.goodsCategoryService.findByName(deptName);
		return result == null;
	}

	@Log("新增商品分类 ")
	@RequiresPermissions("goodscategory:add")
	@RequestMapping("goodscategory/add")
	@ResponseBody
	public ResponseBo addRole(GoodsCategory category) {
		try {
			this.goodsCategoryService.addGoodsCategory(category);
			return ResponseBo.ok("新增商品分类成功！");
		} catch (Exception e) {
			log.error("新增商品分类失败", e);
			return ResponseBo.error("新增商品分类失败，请联系网站管理员！");
		}
	}

	@Log("删除商品分类")
	@RequiresPermissions("goodscategory:delete")
	@RequestMapping("goodscategory/delete")
	@ResponseBody
	public ResponseBo deleteDepts(String ids) {
		try {
			this.goodsCategoryService.deleteGoodsCategorys(ids);
			return ResponseBo.ok("删除商品分类成功！");
		} catch (Exception e) {
			log.error("删除商品分类失败", e);
			return ResponseBo.error("删除商品分类失败，请联系网站管理员！");
		}
	}

	@Log("修改商品分类 ")
	@RequiresPermissions("goodscategory:update")
	@RequestMapping("goodscategory/update")
	@ResponseBody
	public ResponseBo updateRole(GoodsCategory category) {
		try {
			this.goodsCategoryService.updateGoodsCategory(category);
			return ResponseBo.ok("修改商品分类成功！");
		} catch (Exception e) {
			log.error("修改商品分类失败", e);
			return ResponseBo.error("修改商品分类失败，请联系网站管理员！");
		}
	}
}
