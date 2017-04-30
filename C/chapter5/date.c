static char daytab[2][13] = {
    {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
    {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
};

int day_of_year(int year, int month, int day)
{
    int i, leap;

    leap = year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    if (month < 1 || month > 12)
        return -1;
    if (day < 1 || day > daytab[leap][month])
        return -1;
    for (i = 1; i < month; i++)
        day  += daytab[leap][i];
    return day;
}

int day_of_year2(int year, int month, int day)
{
    int leap;
    char *p;
    leap = year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    p = daytab[leap];
    if (day < 1 || day > *(p + month))
        return -1;
    while (--month)
        day += *++p;
    return day;
}
void month_day(int year, int yearday, int *pmonth, int *pday)
{
    int i, leap;
    if (year < 1) {
        *pmonth = -1;
        *pday = -1;
        return;
    }
    leap = year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    for (i = 1; i <= 12 && yearday > daytab[leap][i]; i++)
        yearday -= daytab[leap][i];
    if (i > 12 && yearday > daytab[leap][12]) {
        *pmonth = -1;
        *pday = -1;
    }
    else {
        *pmonth = i;
        *pday = yearday;
    }
}
void month_day2(int year, int yearday, int *pmonth, int *pday)
{
    int leap;
    char *p;
    if (year < 1) {
        *pmonth = -1;
        *pday = -1;
        return;
    }
    leap = year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    p = daytab[leap];
    while (yearday > *++p)
        yearday -= *p;
    if (p - *(daytab + leap) > 12) {
        *pmonth = -1;
        *pday = -1;
    } else {
        *pmonth = p - *(daytab + leap);
        *pday = yearday;
    }
}

char *month_name(int n)
{
    static char *name[] = {
        "Illegal month",
        "January", "February", "March",
        "April", "May", "June",
        "July", "August", "September",
        "October", "November", "December"
    };
    return (n < 1 || n > 12) ? name[0] : name[n];
}
