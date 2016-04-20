package tv.vego.dp.model;

/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.github.pagehelper.Page;

/**
 * 瀵筆age<E>缁撴灉杩涜鍖呰
 * <p/>
 * 鏂板鍒嗛〉鐨勫椤瑰睘鎬э紝涓昏鍙傝�:http://bbs.csdn.net/topics/360010907
 *
 * @author liuzh/abel533/isea533
 * @version 3.3.0
 * @since 3.2.2
 * 椤圭洰鍦板潃 : http://git.oschina.net/free/Mybatis_PageHelper
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    //褰撳墠椤�
    private int pageNum;
    //姣忛〉鐨勬暟閲�
    private int pageSize;
    //褰撳墠椤电殑鏁伴噺
    private int size;
    //鎺掑簭
    private String orderBy;

    //鐢变簬startRow鍜宔ndRow涓嶅父鐢紝杩欓噷璇翠釜鍏蜂綋鐨勭敤娉�
    //鍙互鍦ㄩ〉闈腑"鏄剧ずstartRow鍒癳ndRow 鍏眘ize鏉℃暟鎹�

    //褰撳墠椤甸潰绗竴涓厓绱犲湪鏁版嵁搴撲腑鐨勮鍙�
    private int startRow;
    //褰撳墠椤甸潰鏈�悗涓�釜鍏冪礌鍦ㄦ暟鎹簱涓殑琛屽彿
    private int endRow;
    //鎬昏褰曟暟
    private long total;
    //鎬婚〉鏁�
    private int pages;
    //缁撴灉闆�
    private List<T> rows;

    //绗竴椤�
    private int firstPage;
    public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	//鍓嶄竴椤�
    private int prePage;
    //涓嬩竴椤�
    private int nextPage;
    //鏈�悗涓�〉
    private int lastPage;

    //鏄惁涓虹涓�〉
    private boolean isFirstPage = false;
    //鏄惁涓烘渶鍚庝竴椤�
    private boolean isLastPage = false;
    //鏄惁鏈夊墠涓�〉
    private boolean hasPreviousPage = false;
    //鏄惁鏈変笅涓�〉
    private boolean hasNextPage = false;
    //瀵艰埅椤电爜鏁�
    private int navigatePages;
    //鎵�湁瀵艰埅椤靛彿
    private int[] navigatepageNums;

    public PageInfo() {
    }

    /**
     * 鍖呰Page瀵硅薄
     *
     * @param list
     */
    public PageInfo(List<T> list) {
        this(list, 8);
    }

    /**
     * 鍖呰Page瀵硅薄
     *
     * @param list          page缁撴灉
     * @param navigatePages 椤电爜鏁伴噺
     */
    public PageInfo(List<T> list, int navigatePages) {
        if (list instanceof Page) {
            Page page = (Page) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.orderBy = page.getOrderBy();

            this.pages = page.getPages();
            this.rows = page;
            this.size = page.size();
            this.total = page.getTotal();
            //鐢变簬缁撴灉鏄�startRow鐨勶紝鎵�互瀹為檯鐨勯渶瑕�1
            if (this.size == 0) {
                this.startRow = 0;
                this.endRow = 0;
            } else {
                this.startRow = page.getStartRow() + 1;
                //璁＄畻瀹為檯鐨別ndRow锛堟渶鍚庝竴椤电殑鏃跺�鐗规畩锛�
                this.endRow = this.startRow - 1 + this.size;
            }
        } else if (rows instanceof Collection) {
            this.pageNum = 1;
            this.pageSize = rows.size();

            this.pages = 1;
            this.rows = rows;
            this.size = rows.size();
            this.total = rows.size();
            this.startRow = 0;
            this.endRow = rows.size() > 0 ? rows.size() - 1 : 0;
        }
        if (rows instanceof Collection) {
            this.navigatePages = navigatePages;
            //璁＄畻瀵艰埅椤�
            calcNavigatepageNums();
            //璁＄畻鍓嶅悗椤碉紝绗竴椤碉紝鏈�悗涓�〉
            calcPage();
            //鍒ゆ柇椤甸潰杈圭晫
            judgePageBoudary();
        }
    }

    /**
     * 璁＄畻瀵艰埅椤�
     */
    private void calcNavigatepageNums() {
        //褰撴�椤垫暟灏忎簬鎴栫瓑浜庡鑸〉鐮佹暟鏃�
        if (pages <= navigatePages) {
            navigatepageNums = new int[pages];
            for (int i = 0; i < pages; i++) {
                navigatepageNums[i] = i + 1;
            }
        } else { //褰撴�椤垫暟澶т簬瀵艰埅椤电爜鏁版椂
            navigatepageNums = new int[navigatePages];
            int startNum = pageNum - navigatePages / 2;
            int endNum = pageNum + navigatePages / 2;

            if (startNum < 1) {
                startNum = 1;
                //(鏈�墠navigatePages椤�
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            } else if (endNum > pages) {
                endNum = pages;
                //鏈�悗navigatePages椤�
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatepageNums[i] = endNum--;
                }
            } else {
                //鎵�湁涓棿椤�
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            }
        }
    }

    /**
     * 璁＄畻鍓嶅悗椤碉紝绗竴椤碉紝鏈�悗涓�〉
     */
    private void calcPage() {
        if (navigatepageNums != null && navigatepageNums.length > 0) {
            firstPage = navigatepageNums[0];
            lastPage = navigatepageNums[navigatepageNums.length - 1];
            if (pageNum > 1) {
                prePage = pageNum - 1;
            }
            if (pageNum < pages) {
                nextPage = pageNum + 1;
            }
        }
    }

    /**
     * 鍒ゅ畾椤甸潰杈圭晫
     */
    private void judgePageBoudary() {
        isFirstPage = pageNum == 1;
        isLastPage = pageNum == pages;
        hasPreviousPage = pageNum > 1;
        hasNextPage = pageNum < pages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }


    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("pageNum=").append(pageNum);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", size=").append(size);
        sb.append(", startRow=").append(startRow);
        sb.append(", endRow=").append(endRow);
        sb.append(", total=").append(total);
        sb.append(", pages=").append(pages);
        sb.append(", rows=").append(rows);
        sb.append(", firstPage=").append(firstPage);
        sb.append(", prePage=").append(prePage);
        sb.append(", nextPage=").append(nextPage);
        sb.append(", lastPage=").append(lastPage);
        sb.append(", isFirstPage=").append(isFirstPage);
        sb.append(", isLastPage=").append(isLastPage);
        sb.append(", hasPreviousPage=").append(hasPreviousPage);
        sb.append(", hasNextPage=").append(hasNextPage);
        sb.append(", navigatePages=").append(navigatePages);
        sb.append(", navigatepageNums=");
        if (navigatepageNums == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < navigatepageNums.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(navigatepageNums[i]);
            sb.append(']');
        }
        sb.append('}');
        return sb.toString();
    }
}
