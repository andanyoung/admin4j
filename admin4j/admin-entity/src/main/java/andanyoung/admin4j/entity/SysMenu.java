package andanyoung.admin4j.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysMenu)实体类
 *
 * @author andyoung
 * @since 2021-02-10 23:15:14
 */
@Data
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 688047637088041707L;

    private Integer id;

    private String url;

    private String path;

    private String component;
    /**
     * the name is used by <keep-alive> (must set!!!)
     */
    private String name;
    /**
     * if set noRedirect will no redirect in the breadcrumb
     */
    private String redirect;
    /**
     * if set true, will always show the root menu
     * if not set alwaysShow, when item has more than one children route,
     * it will becomes nested mode, otherwise not show the root menu
     */
    private Object alwaysShow;
    /**
     * if set true, item will not show in the sidebar(default is false)
     */
    private Object hidden;
    /**
     * the icon show in the sidebar
     */
    private String metaIcon;
    /**
     * the name show in sidebar and breadcrumb (recommend set)
     */
    private String metaTitle;
    /**
     * if set false, the page will no be cached(default is true)
     */
    private Object metaCache;
    /**
     * if set true, the tag will affix in the tags-view
     */
    private Object metaAffix;
    /**
     * if set false, the item will hidden in breadcrumb(default is true)
     */
    private Object metaBreadcrumb;
    /**
     * if set path, the sidebar will highlight the path you set
     */
    private String metaActiveMenu;

    private Integer parentId;

    @JsonIgnore
    private Object isDelete;

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Date updateTime;
}
