export function formatCurrency(value: number) {
  return "R$" + value.toFixed(2).replace(".", ",");
}
