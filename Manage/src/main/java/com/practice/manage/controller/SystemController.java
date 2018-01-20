package com.practice.manage.controller;

import com.practice.dto.KeyValueDTO;
import com.practice.dto.PageSearchParam;
import com.practice.enums.DicParentEnum;
import com.practice.manage.target.ControllerPermisson;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xushd  2017/12/22 23:44
 */
@RequestMapping(value = "/auth/system")
@Controller
public class SystemController {

    @Resource
    private UserService userService;
    @Resource
    private DictionaryService dictionaryService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private MenuService menuService;
    @Resource
    private VersionService versionService;
    @Resource
    private ParamService paramService;
    /**
     * User index
     * @return
     */
    @RequestMapping(value = "/user")
    public String indexUser(){

        return "system/user";
    }

    /**
     * User list
     * @param param
     * @return
     */
    @RequestMapping(value = "/user/list")
    @ResponseBody
    public JsonResult ajaxUserList(PageSearchParam param){

        return userService.listUser(param);
    }

    /**
     * User obj
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}")
    @ResponseBody
    public JsonResult ajaxUserObj(@PathVariable Long id){
        return userService.getUser(id);
    }

    /**
     * User add
     * @param token
     * @param manageUser
     * @return
     */
    @RequestMapping(value = "/user/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxUserAdd(@RequestAttribute String token, ManageUser manageUser){

        return userService.addUser(manageUser,token);
    }

    /**
     * User update
     * @param token
     * @param manageUser
     * @return
     */
    @RequestMapping(value = "/user/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxUserUpdate(@RequestAttribute String token,ManageUser manageUser){

        return userService.updateUser(manageUser,token);
    }

    /**
     * User delete
     * @param token
     * @param manageUser
     * @return
     */
    @RequestMapping(value = "/user/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxUserDelete(@RequestAttribute String token,ManageUser manageUser){

        return userService.updateUser(manageUser,token);
    }


    /**
     * List user role
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/role/{id}")
    @ResponseBody
    public JsonResult ajaxUserRole(@PathVariable Long id){

        return userService.listRoleById(id);
    }

    /**
     * Save user role
     * @param token
     * @param id
     * @param roles
     * @return
     */
    @RequestMapping(value = "/user/role/save/{id}")
    @ResponseBody
    public JsonResult ajaxUserRoleSave(@RequestAttribute String token,
                                       @PathVariable Long id, String roles){
        return userService.saveUserRole(token,id,roles);
    }


    /**
     * Dictionary index
     * @return
     */
    @RequestMapping(value = "/dictionary")
    public String indexDictionary(Model model){

        List<DicParentEnum> all = DicParentEnum.getAll();

        model.addAttribute("parent",all);

        return "system/dictionary";
    }

    /**
     * Dictionary parent
     * @return
     */
    @RequestMapping(value = "/dictionary/parent")
    @ResponseBody
    public JsonResult ajaxDictionaryParentList(){
        List<DicParentEnum> all = DicParentEnum.getAll();

        List<KeyValueDTO> list = new ArrayList<>();
        for (DicParentEnum dicParentEnum : all) {
            list.add(new KeyValueDTO(dicParentEnum.getId(),dicParentEnum.getName()));
        }

        return JsonResult.success(list);
    }

    /**
     * List dictionary by type
     * @param key
     * @return
     */
    @RequestMapping(value = "/dictionary/type/{key}")
    @ResponseBody
    public JsonResult ajaxDeictionaryByType(@PathVariable String key){

        List<ManageDictionary> manageDictionaries = dictionaryService.listDictionaryByEnumFromCache(DicParentEnum.stateOf(key));

        return JsonResult.success(manageDictionaries);
    }

    /**
     * Dictionary list
     * @param param
     * @return
     */
    @ControllerPermisson(value = "dictionary/list")
    @RequestMapping(value = "/dictionary/list",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxDictionaryList(PageSearchParam param){
        return dictionaryService.listDictionary(param);
    }


    /**
     * Dictioanry add
     * @param token
     * @param dictionary
     * @return
     */
    @ControllerPermisson(value = "dictionary/add")
    @RequestMapping(value = "/dictionary/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxDictionaryAdd(@RequestAttribute String token,ManageDictionary dictionary){

        return dictionaryService.addDictionary(token,dictionary);
    }

    /**
     * Dictionary obj
     * @param id
     * @return
     */
    @RequestMapping(value = "/dictionary/{id}")
    @ResponseBody
    public JsonResult ajaxDictionaryObj(@PathVariable Long id){
        return dictionaryService.getDictionary(id);
    }

    /**
     * Dictioanry update
     * @param token
     * @param dictionary
     * @return
     */
    @ControllerPermisson(value = "dictionary/update")
    @RequestMapping(value = "/dictionary/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxDictionaryUpdate(@RequestAttribute String token,ManageDictionary dictionary){

        return dictionaryService.updateDictionary(token,dictionary);
    }

    /**
     * Dictioanry delete
     * @param token
     * @param dictionary
     * @return
     */
    @ControllerPermisson(value = "dictionary/delete")
    @RequestMapping(value = "/dictionary/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxDictionaryDelete(@RequestAttribute String token,ManageDictionary dictionary){

        return dictionaryService.updateDictionary(token,dictionary);
    }

    /**
     * Dictionary cache
     * @return
     */
    @ControllerPermisson(value = "dictionary/reset/cache")
    @RequestMapping(value = "/dictionary/reset/cache")
    @ResponseBody
    public JsonResult ajaxDictionaryCacheReset(){
        return dictionaryService.resetDictionaryCache();
    }

    /**
     * Role index
     * @return
     */
    @RequestMapping(value = "/role")
    public String indexRole(){

        return "system/role";
    }

    /**
     * Role list
     * @param param
     * @return
     */
    @RequestMapping(value = "/role/list",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxRoleList(PageSearchParam param){
        return roleService.listRole(param);
    }

    /**
     * Role obj
     * @param id
     * @return
     */
    @RequestMapping(value = "/role/{id}")
    @ResponseBody
    public JsonResult ajaxRoleObj(@PathVariable Long id){
        return roleService.getRole(id);
    }

    /**
     * Role add
     * @param token
     * @param manageRole
     * @return
     */
    @RequestMapping(value = "/role/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxRoleAdd(@RequestAttribute String token, ManageRole manageRole){
        return roleService.addRole(token,manageRole);
    }

    /**
     * Role update
     * @param token
     * @param manageRole
     * @return
     */
    @RequestMapping(value = "/role/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxRoleUpdate(@RequestAttribute String token,ManageRole manageRole){
        return roleService.updateRole(token,manageRole);
    }

    /**
     * Role delete
     * @param token
     * @param manageRole
     * @return
     */
    @RequestMapping(value = "/role/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxRoleDelete(@RequestAttribute String token,ManageRole manageRole){
        return roleService.updateRole(token,manageRole);
    }

    /**
     * Menu index
     * @return
     */
    @RequestMapping(value = "/menu")
    public String indexMenu(){

        return "system/menu";
    }

    /**
     * Menu list
     * @return
     */
    @RequestMapping(value = "/menu/list")
    @ResponseBody
    public JsonResult ajaxMenuList(){
        return menuService.listMenu();
    }

    /**
     * Menu obj
     * @param id
     * @return
     */
    @RequestMapping(value = "/menu/{id}")
    @ResponseBody
    public JsonResult ajaxMenuObj(@PathVariable Long id){
        return menuService.getMenu(id);
    }

    /**
     * Menu add
     * @param token
     * @param manageNav
     * @return
     */
    @RequestMapping(value = "/menu/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxMenuAdd(@RequestAttribute String token, ManageNav manageNav){
        return menuService.addMenu(token,manageNav);
    }

    @RequestMapping(value = "/menu/parent/list")
    @ResponseBody
    public JsonResult ajaxMenuParent(){
        return menuService.listMenuParent();
    }

    /**
     * Menu update
     * @param token
     * @param manageNav
     * @return
     */
    @RequestMapping(value = "/menu/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxMenuUpdate(@RequestAttribute String token,ManageNav manageNav){
        return menuService.updateMenu(token,manageNav);
    }

    /**
     * Menu delete
     * @param token
     * @param manageNav
     * @return
     */
    @RequestMapping(value = "/menu/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxMenuDelete(@RequestAttribute String token,ManageNav manageNav){
        return menuService.updateMenu(token,manageNav);
    }

    /**
     * Permission index
     * @return
     */
    @RequestMapping(value = "/permission")
    public String indexPermission(){
        return "system/permission";
    }

    /**
     * Permission list
     * @param param
     * @return
     */
    @RequestMapping(value = "/permission/list",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxPermissionList(PageSearchParam param){
        return permissionService.listPermission(param);
    }

    /**
     * Permission obj
     * @param id
     * @return
     */
    @RequestMapping(value = "/permission/{id}")
    @ResponseBody
    public JsonResult ajaxPermisssionObj(@PathVariable Long id){
        return permissionService.getPermission(id);
    }

    /**
     * Permission add
     * @param token
     * @param managePermission
     * @return
     */
    @RequestMapping(value = "/permission/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxPermissionAdd(@RequestAttribute String token, ManagePermission managePermission){
        return permissionService.addPermission(token,managePermission);
    }

    /**
     * Permission update
     * @param token
     * @param managePermission
     * @return
     */
    @RequestMapping(value = "/permission/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxPermissionUpdate(@RequestAttribute String token, ManagePermission managePermission){
        return permissionService.updatePermission(token,managePermission);
    }


    /**
     * Permission delete
     * @param token
     * @param managePermission
     * @return
     */
    @RequestMapping(value = "/permission/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxPermissionDelete(@RequestAttribute String token, ManagePermission managePermission){
        return permissionService.updatePermission(token,managePermission);
    }

    /**
     * Version index
     * @return
     */
    @RequestMapping(value = "/version")
    public String indexVersion(){
        return "system/version";
    }

    /**
     * Version list
     * @param param
     * @return
     */
    @RequestMapping(value = "/version/list",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxVersionList(PageSearchParam param){
        return versionService.listVersion(param);
    }

    /**
     * Version obj
     * @param id
     * @return
     */
    @RequestMapping(value = "/version/{id}")
    @ResponseBody
    public JsonResult ajaxVersionObj(@PathVariable Long id){
        return versionService.getVersion(id);
    }

    /**
     * Version add
     * @param token
     * @param manageVersion
     * @return
     */
    @RequestMapping(value = "/version/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxVersionAdd(@RequestAttribute String token,ManageVersion manageVersion){
        return versionService.addVersion(token,manageVersion);
    }

    /**
     * Version update
     * @param token
     * @param manageVersion
     * @return
     */
    @RequestMapping(value = "/version/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult ajaxVersionUpdate(@RequestAttribute String token,ManageVersion manageVersion){
        return versionService.updateVersion(token,manageVersion);
    }

    /**
     * Version delete
     * @param id
     * @return
     */
    @RequestMapping(value = "/version/delete/{id}")
    @ResponseBody
    public JsonResult ajaxVersionDelete(@PathVariable Long id){
        return versionService.deleteVersion(id);
    }

    /**
     * Version item list
     * @param id
     * @return
     */
    @RequestMapping(value = "/version/item/list/{id}")
    @ResponseBody
    public JsonResult ajaxVersionItemList(@PathVariable Long id){
        return versionService.listVersionItem(id);
    }


    /**
     * Menu usable list
     * @return
     */
    @RequestMapping(value = "/menu/usable/list/{id}")
    @ResponseBody
    public JsonResult ajaxMenuUsableList(@PathVariable Long id){
        return menuService.listMenuUsable(id);
    }

    /**
     * permission usable list
     * @return
     */
    @RequestMapping(value = "/permission/usable/list/{id}")
    @ResponseBody
    public JsonResult ajaxPermissionUsableList(@PathVariable Long id){
        return permissionService.listPermissionUsable(id);
    }

    /**
     * Role usable list
     * @return
     */
    @RequestMapping(value = "/role/usable/list/{id}")
    @ResponseBody
    public JsonResult ajaxRoleUsableList(@PathVariable Long id){
        return roleService.listRoleUsable(id);
    }

    /**
     * Role nav and permisssion save
     * @param token
     * @param id
     * @param navs
     * @param pers
     * @return
     */
    @RequestMapping(value = "/role/navpermission/save/{id}")
    @ResponseBody
    public JsonResult ajaxRoleNavPermissionSave(@RequestAttribute String token,
                                                @PathVariable Long id,
                                                String navs,String pers){
        return roleService.saveRoleNavAndPermission(token,id,navs,pers);
    }

    /**
     * Param index
     * @return
     */
    @RequestMapping(value = "/param")
    public String indexParam(){

        return "system/param";
    }

    /**
     * Param list
     * @param param
     * @return
     */
    @RequestMapping(value = "/param/list")
    @ResponseBody
    public JsonResult ajaxParamList(PageSearchParam param){
        return paramService.listParam(param);
    }

    /**
     * Param save
     * @param param
     * @param token
     * @return
     */
    @RequestMapping(value = "/param/add")
    @ResponseBody
    public JsonResult ajaxParamSave(SystemParam param,@RequestAttribute String token){
        return paramService.saveParam(param,token);
    }

    /**
     * Param update
     * @param param
     * @param token
     * @return
     */
    @RequestMapping(value = "/param/update")
    @ResponseBody
    public JsonResult ajaxParamUpdate(SystemParam param,@RequestAttribute String token){
        return paramService.updateParam(param,token);
    }

    /**
     * Param del
     * @param id
     * @param token
     * @return
     */
    @RequestMapping(value = "/param/del/{id}")
    @ResponseBody
    public JsonResult ajaxParamDel(@PathVariable Long id,@RequestAttribute String token){
        return paramService.delParam(id,token);
    }

    /**
     * Param sync cache
     * @return
     */
    @RequestMapping(value = "/param/sync/cache")
    @ResponseBody
    public JsonResult ajaxParamSyncCache(){
        return paramService.syncParamCache();
    }

    /**
     * Param get
     * @param id
     * @return
     */
    @RequestMapping(value = "/param/{id}")
    @ResponseBody
    public JsonResult ajaxParmObj(@PathVariable Long id){
        return paramService.getParam(id);
    }

}
