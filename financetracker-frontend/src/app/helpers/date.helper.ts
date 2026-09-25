export class DateHelper {
  static formatToIsoDate(date: Date): string {
    return date.toISOString().split('T')[0];
  }

  static getFirstDayOfMonth(date: Date = new Date()): string {
    const firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
    return this.formatToIsoDate(firstDay);
  }

  static getLastDayOfMonth(date: Date = new Date()): string {
    const lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
    return this.formatToIsoDate(lastDay);
  }
}
