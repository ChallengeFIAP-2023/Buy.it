import { Flex } from '@global/styles';
import { TextIndicator } from '@screens/QuotesHistory/QuotesList/styles';
import styled from 'styled-components/native';

export const Container = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
`;

export const Title = styled.Text`
  margin-top: 30px;
  margin-bottom: 10px;
  color: ${({ theme }) => theme.COLORS.WHITE};
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};
  font-size: ${({ theme }) => theme.FONT_SIZE.XL}px;
`;

export const QuotesWrapper = styled.View`
  background-color: ${({ theme }) => theme.COLORS.GRAY_500};
  padding: 16px;
  border-radius: 10px;
`;

export const Indicator = styled(TextIndicator)`
  margin: 30px 0;
`;

export const Actions = styled(Flex)`
  margin-top: 20px;
`;