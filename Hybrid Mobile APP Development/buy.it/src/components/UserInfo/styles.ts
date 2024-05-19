import styled from "styled-components/native";

export const CompanyWrapper = styled.View`
  flex-direction: row;
  align-items: center;
  gap: 16px;

  padding: 20px 30px;
`;

export const CompanyAvatar = styled.View`
  flex: 2;
`;

export const CompanyData = styled.View`
  display: flex;
  flex-direction: column;
  align-items: start;
  gap: 5px;
  flex: 4;
`;

export const CompanyName = styled.Text`
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-size: ${({ theme }) => theme.FONT_SIZE.XL}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};

  margin-bottom: 12px;

  text-transform: capitalize;
`;

export const CompanyDocument = styled.Text`
  color: ${({ theme }) => theme.COLORS.GRAY_300};
  font-size: ${({ theme }) => theme.FONT_SIZE.SM}px;
`;