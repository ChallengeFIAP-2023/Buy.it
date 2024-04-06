import { Chip } from '@components/Chip';
import styled from 'styled-components/native';

export const Container = styled.View`
  display: flex;
  flex-direction: row;
  justify-content: space-between;

  background-color: ${({ theme }) => theme.COLORS.GRAY_500};

  padding: 16px;
  margin-top: 20px;

  border-radius: 10px;

  min-height: 70px;
`;

export const QuoteTag = styled(Chip)`
  padding: 2px 10px;
`;

export const QuoteName = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.BOLD};
  color: ${({ theme }) => theme.COLORS.WHITE};
`;

export const QuoteDate = styled(QuoteName)`
  font-family: ${({ theme }) => theme.FONT_FAMILY.ROBOTO.BOLD};
  font-size: ${({ theme }) => theme.FONT_SIZE.XXS}px;
  color: ${({ theme }) => theme.COLORS.GRAY_100};
`;

export const QuoteQuantity = styled(QuoteDate)`
  font-size: ${({ theme }) => theme.FONT_SIZE.XS}px;
`;

export const QuotePrice = styled(QuoteName)`
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.SEMIBOLD};
  font-size: ${({ theme }) => theme.FONT_SIZE.XXL}px;
  color: ${({ theme }) => theme.COLORS.WHITE};
`;

export const QuotePriceSymbol = styled(QuoteName)``;

export const Section = styled.View<{ start?: boolean; end?: boolean }>`
  display: flex;
  flex-direction: column;
  justify-content: center;

  align-items: ${props => {
    if (props.end) return 'flex-end';
    if (props.start) return 'flex-start';

    return 'center';
  }};

  gap: 10px;
`;

export const QuoteIconContainer = styled.View`
  background-color: ${({ theme }) => theme.COLORS.GRAY_600};

  width: 70px;

  border-radius: 5px;

  flex: 1;
`;
