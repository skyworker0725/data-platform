import java.text.SimpleDateFormat

def sdf = new SimpleDateFormat("yyyyMMdd")
Calendar ca = Calendar.getInstance();

ca.add(Calendar.DAY_OF_MONTH, -1)
def beforeDate = ca.getTime()

sdf.format(beforeDate)