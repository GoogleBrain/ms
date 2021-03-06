package cn.hu.system.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hu.common.annotation.Log;
import cn.hu.common.controller.BaseController;
import cn.hu.common.domain.QueryRequest;
import cn.hu.common.domain.ResponseBo;
import cn.hu.common.util.FileUtil;
import cn.hu.common.util.MD5Utils;
import cn.hu.system.domain.ReceiveAddress;
import cn.hu.system.domain.User;
import cn.hu.system.service.ReceiveAddressService;
import cn.hu.system.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * 收件人地址
 * 
 * @author googlebrain
 *
 */
@Controller
public class ReceiveController extends BaseController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private ReceiveAddressService receiveAddressService;

	private static final String ON = "on";

	@RequestMapping("receive")
	@RequiresPermissions("receive:list")
	public String index(Model model) {
		User user = super.getCurrentUser();
		model.addAttribute("user", user);
		return "system/receive/receiveAddress";
	}

	@RequestMapping("receive/checkUserName")
	@ResponseBody
	public boolean checkUserName(String username, String oldusername) {
		if (StringUtils.isNotBlank(oldusername) && username.equalsIgnoreCase(oldusername)) {
			return true;
		}
		User result = this.userService.findByName(username);
		return result == null;
	}

	@Log("获取详细地址信息***")
	@RequestMapping("receive/getReceive")
	@ResponseBody
	public ResponseBo getUser(Integer userId) {
		try {
			ReceiveAddress address = new ReceiveAddress();
			address.setId("" + userId);
			ReceiveAddress receiveAddress = this.receiveAddressService.getReceiveDetails(address).get(0);
			return ResponseBo.ok(receiveAddress);
		} catch (Exception e) {
			log.error("获取详细地址失败", e);
			return ResponseBo.error("获取详细地址失败，请联系网站管理员！");
		}
	}

	@Log("获取地址信息***")
	@RequestMapping("receive/list")
	@RequiresPermissions("receive:list")
	@ResponseBody
	public Map<String, Object> userList(QueryRequest request, User user) {
		ReceiveAddress address = new ReceiveAddress();
		address.setNickname(user.getUsername());
		return super.selectByPageNumSize(request, () -> this.receiveAddressService.getReceiveDetails(address));
	}

	@RequestMapping("receive/excel")
	@ResponseBody
	public ResponseBo userExcel(User user) {
		try {
			List<User> list = this.userService.findUserWithDept(user, null);
			return FileUtil.createExcelByPOIKit("用户表", list, User.class);
		} catch (Exception e) {
			log.error("导出用户信息Excel失败", e);
			return ResponseBo.error("导出Excel失败，请联系网站管理员！");
		}
	}

	@RequestMapping("receive/csv")
	@ResponseBody
	public ResponseBo userCsv(User user) {
		try {
			List<User> list = this.userService.findUserWithDept(user, null);
			return FileUtil.createCsv("用户表", list, User.class);
		} catch (Exception e) {
			log.error("导出用户信息Csv失败", e);
			return ResponseBo.error("导出Csv失败，请联系网站管理员！");
		}
	}

	@RequestMapping("receive/regist")
	@ResponseBody
	public ResponseBo regist(User user) {
		try {
			User result = this.userService.findByName(user.getUsername());
			if (result != null) {
				return ResponseBo.warn("该用户名已被使用！");
			}
			this.userService.registUser(user);
			return ResponseBo.ok();
		} catch (Exception e) {
			log.error("注册失败", e);
			return ResponseBo.error("注册失败，请联系网站管理员！");
		}
	}

	@Log("更换主题")
	@RequestMapping("receive/theme")
	@ResponseBody
	public ResponseBo updateTheme(User user) {
		try {
			this.userService.updateTheme(user.getTheme(), user.getUsername());
			return ResponseBo.ok();
		} catch (Exception e) {
			log.error("修改主题失败", e);
			return ResponseBo.error();
		}
	}

	@Log("新增用户")
	@RequiresPermissions("receive:add")
	@RequestMapping("receive/add")
	@ResponseBody
	public ResponseBo addUser(User user, Long[] roles) {
		try {
			if (ON.equalsIgnoreCase(user.getStatus()))
				user.setStatus(User.STATUS_VALID);
			else
				user.setStatus(User.STATUS_LOCK);
			this.userService.addUser(user, roles);
			return ResponseBo.ok("新增用户成功！");
		} catch (Exception e) {
			log.error("新增用户失败", e);
			return ResponseBo.error("新增用户失败，请联系网站管理员！");
		}
	}

	@Log("修改用户")
	@RequiresPermissions("receive:update")
	@RequestMapping("receive/update")
	@ResponseBody
	public ResponseBo updateUser(User user, Long[] rolesSelect) {
		try {
			if (ON.equalsIgnoreCase(user.getStatus()))
				user.setStatus(User.STATUS_VALID);
			else
				user.setStatus(User.STATUS_LOCK);
			this.userService.updateUser(user, rolesSelect);
			return ResponseBo.ok("修改用户成功！");
		} catch (Exception e) {
			log.error("修改用户失败", e);
			return ResponseBo.error("修改用户失败，请联系网站管理员！");
		}
	}

	@Log("删除用户")
	@RequiresPermissions("receive:delete")
	@RequestMapping("receive/delete")
	@ResponseBody
	public ResponseBo deleteUsers(String ids) {
		try {
			this.userService.deleteUsers(ids);
			return ResponseBo.ok("删除用户成功！");
		} catch (Exception e) {
			log.error("删除用户失败", e);
			return ResponseBo.error("删除用户失败，请联系网站管理员！");
		}
	}

	@RequestMapping("receive/checkPassword")
	@ResponseBody
	public boolean checkPassword(String password) {
		User user = getCurrentUser();
		String encrypt = MD5Utils.encrypt(user.getUsername().toLowerCase(), password);
		return user.getPassword().equals(encrypt);
	}

	@RequestMapping("receive/updatePassword")
	@ResponseBody
	public ResponseBo updatePassword(String newPassword) {
		try {
			this.userService.updatePassword(newPassword);
			return ResponseBo.ok("更改密码成功！");
		} catch (Exception e) {
			log.error("修改密码失败", e);
			return ResponseBo.error("更改密码失败，请联系网站管理员！");
		}
	}

	@RequestMapping("receive/profile")
	public String profileIndex(Model model) {
		User user = super.getCurrentUser();
		user = this.userService.findUserProfile(user);
		String ssex = user.getSsex();
		if (User.SEX_MALE.equals(ssex)) {
			user.setSsex("性别：男");
		} else if (User.SEX_FEMALE.equals(ssex)) {
			user.setSsex("性别：女");
		} else {
			user.setSsex("性别：保密");
		}
		model.addAttribute("user", user);
		return "system/receive/receiveProfile";
	}

	@RequestMapping("receive/getUserProfile")
	@ResponseBody
	public ResponseBo getUserProfile(Long userId) {
		try {
			User user = new User();
			user.setUserId(userId);
			return ResponseBo.ok(this.userService.findUserProfile(user));
		} catch (Exception e) {
			log.error("获取用户信息失败", e);
			return ResponseBo.error("获取用户信息失败，请联系网站管理员！");
		}
	}
}
