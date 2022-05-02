package com.example.inspiration.httpUtils
import java.io.*

/**
 *
 * @ClassName:      Response
 * @Author:         Yan
 * @CreateDate:     12点33分
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    Data 数据
 */

open class ApiResponse<out T>(
    open val data: T? = null,
    open val code: Int? = null,
    open val msg: String? = null,
    open val error: Throwable? = null,
) : Serializable {
    val isSuccess: Boolean
        get() = code == 0 || code == 200 || code == 114
}

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val response: T) : ApiResponse<T>(data = response)

data class ApiFailedResponse<T>(override val code: Int?, override val msg: String?) : ApiResponse<T>(code = code, msg = msg)

data class ApiErrorResponse<T>(val throwable: Throwable) : ApiResponse<T>(error = throwable)


//User的Token
data class UserToken(
    val refreshToken: String,
    val token: String
)

//每一页的颜色
data class ColorPage(
    val count: Int,
    val list: List<ColorTheme>
)

data class ColorTheme(
    val id: Int,
    val theme: String
)

//获得每一页的颜色ID
data class ColorList(
    val color_list: List<Color>,
    val has_more: Boolean
)

data class Color(
    val b: Int,
    val c: Int,
    val g: Int,
    val hex: String,
    val id: Int,
    val k: Int,
    val m: Int,
    val name: String,
    val r: Int,
    val y: Int
)

//获得颜色的详细页
data class ColorDetail(
    val colors: Colors,
    val intro: String,
    val shades: Shades
)

data class Colors(
    val color_1: Color1,
    val color_2: Color2,
    val color_3: Color3,
    val color_4: Color4,
    val color_5: Color5,
    val color_6: Color6,
    val color_7: Color7
)

data class Shades(
    val shade_list: List<Shade>
)

data class Color1(
    val b: Int,
    val c: Int,
    val g: Int,
    val hex: String,
    val id: Int,
    val k: Int,
    val m: Int,
    val name: String,
    val r: Int,
    val y: Int
)

data class Color2(
    val b: Int,
    val c: Int,
    val g: Int,
    val hex: String,
    val id: Int,
    val k: Int,
    val m: Int,
    val name: String,
    val r: Int,
    val y: Int
)

data class Color3(
    val b: Int,
    val c: Int,
    val g: Int,
    val hex: String,
    val id: Int,
    val k: Int,
    val m: Int,
    val name: String,
    val r: Int,
    val y: Int
)

data class Color4(
    val b: Int,
    val c: Int,
    val g: Int,
    val hex: String,
    val id: Int,
    val k: Int,
    val m: Int,
    val name: String,
    val r: Int,
    val y: Int
)

data class Color5(
    val b: Int,
    val c: Int,
    val g: Int,
    val hex: String,
    val id: Int,
    val k: Int,
    val m: Int,
    val name: String,
    val r: Int,
    val y: Int
)

data class Color6(
    val b: Int,
    val c: Int,
    val g: Int,
    val hex: String,
    val id: Int,
    val k: Int,
    val m: Int,
    val name: String,
    val r: Int,
    val y: Int
)

data class Color7(
    val b: Int,
    val c: Int,
    val g: Int,
    val hex: String,
    val id: Int,
    val k: Int,
    val m: Int,
    val name: String,
    val r: Int,
    val y: Int
)

data class Shade(
    val shade: List<ShadeX>
)

data class ShadeX(
    val color: Color
)

//灵感界面的首页
data class InspirationHome(
    val id: Int,
    val image: String,
    val name: String
)

//灵感的List
data class InspirationList(
    val ideaDetail: Int
)

//灵感的详细类
data class InspirationDetail(
    val colors: Colors,
    val image: String,
    val intro: String,
    val shades: Shades,
    val title: String
)

//收藏列表
data class StarList(
    val has_more: Boolean,
    val star_list: List<Star>
)

data class Star(
    val colorShade: List<ColorShade>,
    val id: Int,
    val name: String
)

data class ColorShade(
    val color: Color
)

