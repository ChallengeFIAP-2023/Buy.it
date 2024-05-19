import styled from 'styled-components/native';

type Props = {
  contained: boolean;
}

export const Container = styled.View<Props>`
  padding: ${({ contained }) => contained ? "5px 0" : "20px 0"};
  border-top-width: ${({ contained }) => contained ? "0" : "1px"};
  border-color: ${({ theme }) => theme.COLORS.GRAY_400};
  display: flex;
  align-items: ${({ contained }) => contained ? "center" : "flex-start"};
  flex-direction: ${({ contained }) => contained ? "row" : "column"};
  gap: ${({ contained }) => contained ? "5px" : "7px"};
`;

export const Label = styled.Text<Props>`
  font-size: ${({ theme }) => theme.FONT_SIZE.LG}px;
  margin-bottom: ${({ contained }) => contained ? "5px" : "10px"};
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.SEMIBOLD};
  color: ${({ theme }) => theme.COLORS.WHITE};
`;

export const Value = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.SM}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.BOLD};
  color: ${({ theme }) => theme.COLORS.GRAY_300};
`;