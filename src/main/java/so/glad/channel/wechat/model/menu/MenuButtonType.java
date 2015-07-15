package so.glad.channel.wechat.model.menu;

/**
 * @author palmtale
 *         on 15/7/10.
 */
public enum MenuButtonType {

    CLICK("click"), VIEW("view"), SCANCODE("scancode_push"), SCANCODEMSG("scancode_waitmsg"),
    PICSYS("pic_sysphoto"), PICALBUM("pic_photo_or_album"), PICWECHAT("pic_weixin"),
    LOCATION("location_select"), MEDIA("media_id"), VIEWLIMITED("view_limited");

    private String caption;

    public String getCaption(){
        return caption;
    }

    public static MenuButtonType valueOfCaption(String caption) {
        for(MenuButtonType type : MenuButtonType.values()){
            if(type.caption.equals(caption)){
                return type;
            }
        }
        return CLICK;
    }

    MenuButtonType(String caption){
        this.caption = caption;
    }


}
